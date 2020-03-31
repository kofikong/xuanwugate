package com.xuanwugate.blockchain.bitcoin.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.requestparams.CreateWallet;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.AddMultisigAddressResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.CreateWalletResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.GetAddressesByLabelResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.GetBalancesResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.ListAddressGroupingsResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.ListLabelsResult;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.LoadWalletResult;
import com.xuanwugate.rpc.RPCResultFactory;
import com.xuanwugate.store.RocksDBUtils;
import com.xuanwugate.store.WalletType;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.WalletInfoResult;
import com.xuanwugate.blockchain.bitcoin.response.CreateWalletResponse;
import com.xuanwugate.blockchain.bitcoin.response.GenerateAddressInWalletResponse;
import com.xuanwugate.blockchain.bitcoin.response.GenerateAddressResponse;
import com.xuanwugate.blockchain.bitcoin.response.GetWalletDetailsResponse;
import com.xuanwugate.blockchain.bitcoin.response.ListNormalWalletResponse;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.config.BlockchainConfig;
import com.xuanwugate.blockchain.core.WalletService;
import com.xuanwugate.response.ErrorInfo;
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

		//put normal wallet to database
		RocksDBUtils.getDB().pushWallet(WalletType.BTC, BlockchainConfig.getInstance().getBitcoinRPCNetwork(),cwr);

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

	private WalletInfoResult getWalletInfo(String walletName){
		if(walletName == null){
			walletName = "";
		}
		
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_WALLET_INFO);
		request.setUriWithWalletName(walletName);
		RPCProxyResponse res = RPCProxy.run(request);
		return RPCResultFactory.parse(WalletInfoResult.class, res.getMessage());
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

	public ListNormalWalletResponse listNormalWallet(){		
		// BitcoinRequest request = new BitcoinRequest();
		// request.setMethod(BitcoinCoreConstants.LIST_WALLETS);
		// RPCProxyResponse res = RPCProxy.run(request);
		// return RPCResultFactory.parse(ListNormalWalletResponse.class, res.getMessage());

		List<String> list = RocksDBUtils.getDB().getWalletList(WalletType.BTC, BlockchainConfig.getInstance().getBitcoinRPCNetwork());
		ListNormalWalletResponse res = new ListNormalWalletResponse();
		res.setPayload(list);
		return res;
	}

	public GetWalletDetailsResponse getWalletDetails(String walletName){
		GetWalletDetailsResponse resp = new GetWalletDetailsResponse();
		GetBalancesResult balances = getbalances(walletName);
		if(balances == null){
			resp.setError(ErrorInfo.WalletNameError(walletName));
			return resp;
		}
		
		resp.setWalletName(walletName);
		resp.setTotalBalance(balances.getTrusted().toString());

		ListAddressGroupingsResult balanceGroup = getBalancesGroupByAddress(walletName);
		resp.setAddresses(balanceGroup.getBalancesGroup());
		return resp;
	}

	public CreateWalletResponse addAddressesToNormalWallet(String walletName,List<String> addresses){
		CreateWalletResponse response = new CreateWalletResponse();
		if(walletName == null){
			response.setError(ErrorInfo.WalletNameError(walletName));
			return response;
		}

		boolean isLoad = tryLoadWallet(walletName);
		if(!isLoad){
			response.setError(ErrorInfo.WalletNameError(walletName));
			return response;
		}

		if(addresses == null || addresses.size() == 0){
			response.setError(ErrorInfo.AddressesInvalidError(""));
			return response;
		}

		//import Privkey
		boolean importPrivkey = importAddressesPrivkey(walletName,addresses);
		if(!importPrivkey){
			response.setError(ErrorInfo.AddressesInvalidError(String.join(",", addresses)));
			return response;
		}

		//list addresses
		List<String> tmpAddresses = getAddresses(walletName);
		response.setAddresses(tmpAddresses);
		response.setWalletName(walletName);
		return response;
	}

	public GenerateAddressInWalletResponse generateAddressInNormalWallet(String walletName){
		GenerateAddressInWalletResponse response = new GenerateAddressInWalletResponse();

		//Generate address info
		BitcoinAddressService aService = new BitcoinAddressService(this.config);
		GenerateAddressResponse addressInfo = aService.generateAddressByWallet(walletName);
		response.setAddress_info(addressInfo);

		//Get normal wallet info
		CreateWalletResponse walletInfo = new CreateWalletResponse();
		List<String> tmpAddresses = getAddresses(walletName);
		walletInfo.setAddresses(tmpAddresses);
		walletInfo.setWalletName(walletName);
		response.setWallet_info(walletInfo);
		return response;
	}

	private boolean tryLoadWallet(String walletName){
		LoadWalletResult res = loadWallet(walletName);
		if(res.getError() == null){
			return true;
		}
		else{
			//-4 重复加载报错
			return res.getError().getCode() == -4;
		}
		
		/**
		 {
			"result": null,
			"error": {
				"code": -4,
				"message": "Wallet file verification failed: Error loading wallet koftest4. Duplicate -wallet filename specified."
			},
			"id": "koftest3"
		}
		 */
	}

	private LoadWalletResult loadWallet(String walletName){
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.LOAD_WALLET);
		request.getParams().add(walletName);
		RPCProxyResponse res = RPCProxy.run(request);
		LoadWalletResult result = RPCResultFactory.parse(LoadWalletResult.class, res.getMessage());
		return result;
	}

	private GetBalancesResult getbalances(String walletName){
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_BALANCES);
		request.setUriWithWalletName(walletName);
		RPCProxyResponse res = RPCProxy.run(request);
		GetBalancesResult balances = RPCResultFactory.parse(GetBalancesResult.class, res.getMessage());
		return balances;
	}

	private ListAddressGroupingsResult getBalancesGroupByAddress(String walletName){
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.LIST_ADDRESS_GROUPINGS);
		request.setUriWithWalletName(walletName);
		RPCProxyResponse res = RPCProxy.run(request);
		ListAddressGroupingsResult balances = RPCResultFactory.parse(ListAddressGroupingsResult.class, res.getMessage());
		return balances;
	}
}