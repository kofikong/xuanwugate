package com.xuanwugate.blockchain.bitcoin.service;
import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.response.BlockResponse;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.BlockService;
import com.xuanwugate.rpc.RPCProxy;
import com.xuanwugate.rpc.RPCProxyResponse;
import com.xuanwugate.rpc.RPCResultFactory;

/**
 * BitcoinBlockService
 */
public class BitcoinBlockService extends BlockService{

	public BitcoinBlockService(EndpointConfig config) {
		super(config);
	}

	public BlockResponse getBlockByHash(String bockHash) {
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_BLOCK);
		request.getParams().add(bockHash);
		RPCProxyResponse res = RPCProxy.run(request);
		BlockResponse block = RPCResultFactory.parse(BlockResponse.class,res.getMessage());
		return block;

		// System.out.println(res);
		// JSONObject jsonObject = JSONObject.parseObject(res.getMessage());
		// if(jsonObject != null){
		// 	String jsonError = jsonObject.getString("error");
		// 	JSONObject jsonResult = jsonObject.getJSONObject("result");

		// 	if(jsonError == null && jsonResult != null){
		// 		BlockResponse obj = BlockResponse.parse(jsonResult);
		// 		return obj;
		// 	}
		// }
		// return null;
	}

	public BlockResponse getBlockByHeight(int height){
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_BLOCK_HASH);
		request.getParams().add(height);
		RPCProxyResponse res = RPCProxy.run(request);
		
		System.out.println(res);
		JSONObject jsonObject = JSONObject.parseObject(res.getMessage());
		if(jsonObject != null){
			String jsonError = jsonObject.getString("error");
			String hash = jsonObject.getString("result");

			if(jsonError == null && hash != null){
				return getBlockByHash(hash);
			}
		}
		return null;
	}

	public BlockResponse getBlockByHeight(String heightStr) {
		int height = Integer.parseInt(heightStr);
		return getBlockByHeight(height);
	}

	public BlockResponse getBlockLatest(){
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_BLOCK_COUNT);
		RPCProxyResponse res = RPCProxy.run(request);
		System.out.println(res);
		JSONObject jsonObject = JSONObject.parseObject(res.getMessage());
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