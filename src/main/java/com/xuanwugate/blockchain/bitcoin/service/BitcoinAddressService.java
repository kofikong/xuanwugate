package com.xuanwugate.blockchain.bitcoin.service;

import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.AddressInfo;
import com.xuanwugate.rpc.RPCResultFactory;
import com.xuanwugate.blockchain.bitcoin.response.GenerateAddressResponse;
import com.xuanwugate.blockchain.bitcoin.response.AddressDetailsResponse;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.AddressService;
import com.xuanwugate.rpc.RPCProxy;
// import com.xuanwugate.util.AddressType;
import com.xuanwugate.rpc.RPCProxyResponse;

/**
 * BitcoinAddressService
 */
public class BitcoinAddressService extends AddressService {

	public BitcoinAddressService(EndpointConfig config) {
		super(config);
	}

	public GenerateAddressResponse generateAddress() {
		// try {
		// 	String newAddress = this.getAddress(AddressType.BIP32ExtendedPublicKey);
		// 	newAddress = "";
		// } catch (Exception e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }

		//Generate address in default wallet
		String address = getNewAddress("");
		String privateKey = dumpPrivkey(address);
		AddressInfo addressInfo = getAddressInfo(address);
		if(address == null || privateKey == null || addressInfo == null){
			return null;
		}

		return GenerateAddressResponse.parse(address,privateKey,addressInfo.getPubkey(),null);
	}

	public GenerateAddressResponse generateAddressByWallet(String walletName) {
		// try {
		// 	String newAddress = this.getAddress(AddressType.BIP32ExtendedPublicKey);
		// 	newAddress = "";
		// } catch (Exception e) {
		// 	// TODO Auto-generated catch block
		// 	e.printStackTrace();
		// }

		//Generate address in the walletName
		String address = getNewAddress(walletName);
		String privateKey = dumpPrivkeyInWallet(walletName,address);
		AddressInfo addressInfo = getAddressInfo(address);
		if(address == null || privateKey == null || addressInfo == null){
			return null;
		}

		return GenerateAddressResponse.parse(address,privateKey,addressInfo.getPubkey(),null);
	}

	private String getNewAddress(String walleName) {
		if(walleName == null){
			walleName = "";
		}

		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_NEW_ADDRESS);
		request.setUriSuffix("/wallet/"+walleName);
		RPCProxyResponse res = RPCProxy.run(request);
		String address = RPCResultFactory.parse(String.class,res.getMessage());
		return address;
	}

	public String dumpPrivkey (String address){
		// Dump private key from default wallet
		return dumpPrivkeyInWallet("",address);
	}

	public String dumpPrivkeyInWallet (String walletName,String address){
		if(address == null){
			return null;
		}

		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.DUMP_PRIV_KEY);
		request.setUriWithWalletName(walletName);
		request.getParams().add(address);
		RPCProxyResponse res = RPCProxy.run(request);
		String privateKey = RPCResultFactory.parse(String.class,res.getMessage());
		return privateKey;
	}

	private AddressInfo getAddressInfo (String address){
		if(address == null){
			return null;
		}

		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_ADDRESS_INFO);
		request.setUriSuffix("/wallet/");
		request.getParams().add(address);
		RPCProxyResponse res = RPCProxy.run(request);
		AddressInfo info = RPCResultFactory.parse(AddressInfo.class,res.getMessage());
		return info;
	}

	public AddressDetailsResponse getAddressDetails(String address){
		return null;
		// BitcoinRequest request = new BitcoinRequest();
		// request.setMethod(BitcoinCoreConstants.GET_ADDRESS_INFO);
		// request.getParams().add(address);
		// String res = RPCProxy.run(request);


		// System.out.println(res);
		// JSONObject jsonObject = JSONObject.parseObject(res);
		// if(jsonObject != null){
		// 	String jsonError = jsonObject.getString("error");
		// 	JSONObject jsonResult = jsonObject.getJSONObject("result");

		// 	if(jsonError == null && address != null){
		// 		return AddressDetailsResponse.parse(jsonResult);
		// 	}
		// }
		// return null;
	}
}