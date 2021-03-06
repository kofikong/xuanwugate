package com.xuanwugate.blockchain.bitcoin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.requestparams.CreateHDWallet;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.AddMultisigAddressResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.CreateWalletResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.GetAddressesByLabelResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.ListLabelsResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.WalletInfoResult;
import com.xuanwugate.rpc.RPCResultFactory;
import com.xuanwugate.blockchain.bitcoin.response.CreateHDWalletResponse;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.HDWalletService;
import com.xuanwugate.response.ErrorInfo;
import com.xuanwugate.rpc.RPCProxy;
import com.xuanwugate.rpc.RPCProxyResponse;

/**
 * BitcoinWalletService
 */
public class BitcoinHDWalletService extends HDWalletService {

	public BitcoinHDWalletService(EndpointConfig config) {
		super(config);
	}

	public CreateHDWalletResponse createHDWallet(CreateHDWallet info) {
		CreateHDWalletResponse response  = new CreateHDWalletResponse();
		if(info == null || !info.valid()){
			response.setError(ErrorInfo.WalletNameError(info != null ? info.getWalletName(): "null"));
			return response;
		}

		//create wallet
		CreateWalletResult cwr = onceCreateHDWallet(info);
		if(cwr == null){
			response.setError(cwr.getError());
			return response;
		}

		// //import Privkey
		// boolean importPrivkey = importAddressesPrivkey(cwr.getName(),info.getAddresses());
		// if(!importPrivkey){
		// 	throw new IllegalArgumentException("invalid addresses");
		// }

		// //list addresses
		// List<String> addresses = getAddresses(cwr.getName());
		// if(addresses.size() < info.getAddresses().size()){
		// 	throw new IllegalArgumentException("invalid walletName or invalid addresses");
		// }

		// CreateWalletResponse response  = new CreateWalletResponse();
		// response.setAddresses(addresses);
		// response.setWalletName(cwr.getName());
		// return response;
		return null;
	}

	private CreateWalletResult onceCreateHDWallet(CreateHDWallet info) {
		if(info == null || !info.valid()){
			return null;
		}

		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.CREATE_WALLET);
		request.getParams().add(info.getWalletName());	//wallet_name
		request.getParams().add(false);	//disable_private_keys 
		request.getParams().add(false);	//blank
		request.getParams().add(info.getPassword());	//passphrase

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

		WalletInfoResult walletInfo = getWalletInfo(walletName);

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

	private WalletInfoResult getWalletInfo(String walletName) {
		if(walletName == null){
			walletName = "";
		}
		
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_WALLET_INFO);
		request.setUriWithWalletName(walletName);
		RPCProxyResponse res = RPCProxy.run(request);
		return RPCResultFactory.parse(WalletInfoResult.class, res.getMessage());
	}

	private List<String> getAddresses(String walletName) {
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

	private GetAddressesByLabelResult getAddressesByLabel(String walletName,String label) {
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

	private ListLabelsResult listLabels(String walletName) {
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