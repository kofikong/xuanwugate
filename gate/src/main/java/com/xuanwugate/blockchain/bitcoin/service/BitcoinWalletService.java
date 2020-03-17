package com.xuanwugate.blockchain.bitcoin.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.request.CreateWallet;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.response.WalletResponse;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.WalletService;
import com.xuanwugate.rpc.RPCProxy;

/**
 * BitcoinWalletService
 */
public class BitcoinWalletService extends WalletService {

	public BitcoinWalletService(EndpointConfig config) {
		super(config);
	}

	public WalletResponse createWallet(CreateWallet info) throws IOException {
		if(info == null || !info.valid()){
			return null;
		}

		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.CREATE_WALLET);
		request.getParams().add(info.getWalletName());
		String res = RPCProxy.run(request);
		System.out.println(res);
		JSONObject jsonObject = JSONObject.parseObject(res);
		if(jsonObject != null){
			String jsonError = jsonObject.getString("error");
			JSONObject jsonResult = jsonObject.getJSONObject("result");

			if(jsonError == null && jsonResult != null){
				// BlockResponse obj = BlockResponse.parse(jsonResult);
				return null;
			}
		}
		return null;
	}
}