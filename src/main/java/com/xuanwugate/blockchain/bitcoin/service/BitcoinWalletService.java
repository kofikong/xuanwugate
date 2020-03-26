package com.xuanwugate.blockchain.bitcoin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.requestparams.CreateWallet;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.AddMultisigAddressResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.CreateWalletResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.GetAddressesByLabelResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.ListLabelsResult;
import com.xuanwugate.rpc.RPCResultFactory;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.WalletInfo;
import com.xuanwugate.blockchain.bitcoin.response.CreateWalletResponse;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.WalletService;
import com.xuanwugate.rpc.ErrorInfo;
import com.xuanwugate.rpc.RPCProxy;
import com.xuanwugate.rpc.RPCProxyResponse;

/**
 * BitcoinWalletService
 */
public class BitcoinWalletService extends WalletService {

	public BitcoinWalletService(EndpointConfig config) {
		super(config);
	}

	public CreateWalletResponse createWallet(CreateWallet info){
		CreateWalletResponse response  = new CreateWalletResponse();

		if(info == null || !info.valid()){
			response.setError(ErrorInfo.WalletNameError(info != null ? info.getWalletName(): "null"));
			return response;
		}

		//create wallet
		CreateWalletResult cwr = onceCreateWallet(info);
		if(cwr.isError()){
			response.setError(cwr.getError());
			return response;
		}

		//import Privkey
		boolean importPrivkey = importAddressesPrivkey(cwr.getName(),info.getAddresses());
		if(!importPrivkey){
			response.setError(ErrorInfo.AddressesInvalidError(String.join(",", info.getAddresses())));
			return response;
		}

		//list addresses
		List<String> addresses = getAddresses(cwr.getName());
		if(addresses.size() < info.getAddresses().size()){
			response.setError(ErrorInfo.WalletNameError(cwr.getName()));
			return response;
		}
		
		response.setAddresses(addresses);
		response.setWalletName(cwr.getName());
		return response;
	}

	private CreateWalletResult onceCreateWallet(CreateWallet info) {
		if(info == null || !info.valid()){
			return null;
		}

		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.CREATE_WALLET);
		request.getParams().add(info.getWalletName());
		RPCProxyResponse res = RPCProxy.run(request);
		return RPCResultFactory.parse(CreateWalletResult.class, res.getMessage());
	}

	private boolean importAddressesPrivkey(String walletName, List<String> addressList){
		BitcoinAddressService aService = new BitcoinAddressService(this.config);
		
		for(String address : addressList){
			String privKey = aService.dumpPrivkey(address);
			if(privKey == null){
				return false;
			}

			BitcoinRequest request = new BitcoinRequest();	
			request.setMethod(BitcoinCoreConstants.IMPORT_PRIVKEY);
			request.setUriWithWalletName(walletName);
			request.getParams().add(privKey);
			request.getParams().add("");
			request.getParams().add(false);
			RPCProxyResponse res = RPCProxy.run(request);
			JSONObject jsonRes = JSONObject.parseObject(res.getMessage());
			if(jsonRes == null || 
				!jsonRes.containsKey("result") ||
				jsonRes.getString("result") != null || 
				!jsonRes.containsKey("error") ||
				jsonRes.getString("error") != null){
				return false;
			}
		}
		
		return true;
	}
 
	private boolean addMultisigAddress(String walletName, List<String> addressList) {
		if(walletName == null){
			walletName = "";
		}

		if(addressList == null || addressList.size() < 1){
			return false;
		}

		WalletInfo walletInfo = getWalletInfo(walletName);

		if(walletInfo == null || !walletName.equals(walletInfo.getWalletname())){
			return false;
		}

		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.ADD_MULTISIG_ADDRESS);
		request.setUriWithWalletName(walletName);
		// [2, "[\"16sSauSf5pF2UkUwvKGq4qjNRzBZYqgEL5\",\"171sgjn4YtPu27adkKGrdDwzRTxnRkBfKV\"]"]
		request.getParams().add(addressList.size());
		request.getParams().add(addressList.toArray(new String[addressList.size()]));
		RPCProxyResponse res = RPCProxy.run(request);
		AddMultisigAddressResult result = RPCResultFactory.parse(AddMultisigAddressResult.class, res.getMessage());
		return result!= null && result.getError() == null;
	}

	private WalletInfo getWalletInfo(String walletName){
		if(walletName == null){
			walletName = "";
		}
		
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_WALLET_INFO);
		request.setUriWithWalletName(walletName);
		RPCProxyResponse res = RPCProxy.run(request);
		return RPCResultFactory.parse(WalletInfo.class, res.getMessage());
	}

	private List<String> getAddresses(String walletName){
		List<String> allAddresses = new ArrayList<String>();
		ListLabelsResult labels = listLabels(walletName);
		for(String label : labels.getLabels()){
			GetAddressesByLabelResult addresses =  getAddressesByLabel(walletName,label);
			for(String key : addresses.getAddresses()){
				allAddresses.add(key);	
			}
		}

		return allAddresses;
	} 

	private GetAddressesByLabelResult getAddressesByLabel(String walletName,String label){
		if(walletName == null){
			walletName = "";
		}
		
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_ADDRESSES_BY_LABEL);
		request.setUriWithWalletName(walletName);
		request.getParams().add(label);
		RPCProxyResponse res = RPCProxy.run(request);
		return RPCResultFactory.parse(GetAddressesByLabelResult.class, res.getMessage());
	}

	private ListLabelsResult listLabels(String walletName){
		if(walletName == null){
			walletName = "";
		}
		
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.LIST_LABELS);
		request.setUriWithWalletName(walletName);
		RPCProxyResponse res = RPCProxy.run(request);
		return RPCResultFactory.parse(ListLabelsResult.class, res.getMessage());
	}
}