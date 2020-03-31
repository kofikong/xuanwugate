package com.xuanwugate.blockchain.bitcoin.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.bitcoin.response.GeneralInformationResponse;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.constants.BlockchainConstants;
import com.xuanwugate.blockchain.core.BlockchainService;
import com.xuanwugate.rpc.RPCProxy;
import com.xuanwugate.rpc.RPCProxyResponse;

/**
 * BitcoinBlockchainService
 */
public class BitcoinBlockchainService extends BlockchainService {
    public BitcoinBlockchainService(EndpointConfig config) {
        super(config);
    }
      
    public GeneralInformationResponse getNodeInformation() {
        BitcoinRequest request = new BitcoinRequest();
        request.setMethod(BitcoinCoreConstants.GET_NODE_INFORMATION);
        RPCProxyResponse res = RPCProxy.run(request);
        JSONObject jsonObject = JSONObject.parseObject(res.getMessage());
        JSONObject jsonResult = jsonObject.getJSONObject("result");
        GeneralInformationResponse obj = GeneralInformationResponse.parse(jsonResult);
        obj.setCurrency(BlockchainConstants.BITCOIN.toUpperCase());
        return obj;
    }
}