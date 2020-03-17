package com.xuanwugate.blockchain.bitcoin.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.AddressInfo;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.RPCResult;
import com.xuanwugate.blockchain.bitcoin.response.GenerateAddressResponse;
import com.xuanwugate.blockchain.bitcoin.response.AddressDetailsResponse;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.AddressService;
import com.xuanwugate.rpc.RPCProxy;

/**
 * BitcoinAddressService
 */
public class BitcoinAddressService extends AddressService {

	public BitcoinAddressService(EndpointConfig config) {
		super(config);
	}

	public GenerateAddressResponse generateAddress() throws IOException {
		String address = getNewAddress();
		String privateKey = dumpPrivkey(address);
		AddressInfo addressInfo = getAddressInfo(address);
		if(address == null || privateKey == null || addressInfo == null){
			return null;
		}

		return GenerateAddressResponse.parse(address,privateKey,addressInfo.getPubkey(),null);
	}

	private String getNewAddress() throws IOException {
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_NEW_ADDRESS);
		String res = RPCProxy.run(request);
		String address = RPCResult.parse(String.class,res);
		return address;
	}

	private String dumpPrivkey (String address) throws IOException{
		if(address == null){
			return null;
		}

		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.DUMP_PRIV_KEY);
		request.getParams().add(address);
		String res = RPCProxy.run(request);
		System.out.println(res);
		String privateKey = RPCResult.parse(String.class,res);
		return privateKey;
	}

	private AddressInfo getAddressInfo (String address) throws IOException{
		if(address == null){
			return null;
		}

		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_ADDRESS_INFO);
		request.getParams().add(address);
		String res = RPCProxy.run(request);
		AddressInfo info = RPCResult.parse(AddressInfo.class,res);
		return info;
	}

	public AddressDetailsResponse getAddressDetails(String address) throws IOException {
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