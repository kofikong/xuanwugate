package com.xuanwugate.blockchain.bitcoin.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.response.BlockResponse;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.BlockService;
import com.xuanwugate.rpc.RPCProxy;

/**
 * BitcoinBlockService
 */
public class BitcoinBlockService extends BlockService{

	public BitcoinBlockService(EndpointConfig config) {
		super(config);
	}

	public BlockResponse getBlockByHash(String bockHash) throws IOException {
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_BLOCK);
		request.getParams().add(bockHash);
		String res = RPCProxy.run(request);
		System.out.println(res);
		JSONObject jsonObject = JSONObject.parseObject(res);
		if(jsonObject != null){
			String jsonError = jsonObject.getString("error");
			JSONObject jsonResult = jsonObject.getJSONObject("result");

			if(jsonError == null && jsonResult != null){
				BlockResponse obj = BlockResponse.parse(jsonResult);
				return obj;
			}
		}
		return null;
	}

	public BlockResponse getBlockByHeight(int height) throws IOException {
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_BLOCK_HASH);
		request.getParams().add(height);
		String res = RPCProxy.run(request);
		System.out.println(res);
		JSONObject jsonObject = JSONObject.parseObject(res);
		if(jsonObject != null){
			String jsonError = jsonObject.getString("error");
			String hash = jsonObject.getString("result");

			if(jsonError == null && hash != null){
				return getBlockByHash(hash);
			}
		}
		return null;
	}

	public BlockResponse getBlockByHeight(String heightStr) throws IOException {
		int height = Integer.parseInt(heightStr);
		return getBlockByHeight(height);
	}

	public BlockResponse getBlockLatest() throws IOException {
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_BLOCK_COUNT);
		String res = RPCProxy.run(request);
		System.out.println(res);
		JSONObject jsonObject = JSONObject.parseObject(res);
		if(jsonObject != null){
			String jsonError = jsonObject.getString("error");
			int count = jsonObject.getInteger("result");

			if(jsonError == null && count > 0){
				return getBlockByHeight(count);
			}
		}
		return null;
	}

	
	
}