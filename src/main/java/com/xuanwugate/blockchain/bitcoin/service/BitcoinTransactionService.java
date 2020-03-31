package com.xuanwugate.blockchain.bitcoin.service;

import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.response.GetRawTransactionHexResponse;
import com.xuanwugate.blockchain.bitcoin.response.GetTransactionDetailsResponse;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.GetRawTransactionResult;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.TransactionService;
import com.xuanwugate.response.ErrorInfo;
import com.xuanwugate.rpc.RPCProxy;
import com.xuanwugate.rpc.RPCProxyResponse;
import com.xuanwugate.rpc.RPCResultFactory;

/**
 * BitcoinTransactionService
 */
public class BitcoinTransactionService extends TransactionService {

	public BitcoinTransactionService(EndpointConfig config) {
		super(config);
	}

	public GetTransactionDetailsResponse getTransactionDetailsByTxid(String txid){
		GetTransactionDetailsResponse response = new GetTransactionDetailsResponse();

		//return transaction details string when second param set true
		String raw = getRawTransationByTexid(txid,true);
		GetRawTransactionResult hexResp = RPCResultFactory.parse(GetRawTransactionResult.class,raw);
		response.init(hexResp);
		return response;
	}


	public GetRawTransactionHexResponse getRawTransactionHexByTxid(String txid){
		if(txid == null || txid.length() != 64){
			GetRawTransactionHexResponse response = new GetRawTransactionHexResponse();	
			response.setError(ErrorInfo.TransactionIdInvalidError());
			return response;
		}
		
		//return hex string when second param set false
		String raw = getRawTransationByTexid(txid,false);
		GetRawTransactionHexResponse hexResp = RPCResultFactory.parse(GetRawTransactionHexResponse.class,raw);
		return hexResp;
	}

	private String getRawTransationByTexid(String txid,boolean isDecode){
		BitcoinRequest request = new BitcoinRequest();
		request.setMethod(BitcoinCoreConstants.GET_RAW_TRANSACTION);
		request.getParams().add(txid);
		request.getParams().add(isDecode);
		RPCProxyResponse res = RPCProxy.run(request);
		return res.getMessage();
	}
}