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

/**
 * BitcoinBlockchainService
 */
public class BitcoinBlockchainService extends BlockchainService {
    public BitcoinBlockchainService(EndpointConfig config) {
        super(config);
    }
      
    public GeneralInformationResponse getNodeInformation() throws IOException {
        BitcoinRequest request = new BitcoinRequest();
        request.setMethod(BitcoinCoreConstants.GET_NODE_INFORMATION);
        String res = RPCProxy.run(request);
        JSONObject jsonObject = JSONObject.parseObject(res);
        JSONObject jsonResult = jsonObject.getJSONObject("result");
        GeneralInformationResponse obj = GeneralInformationResponse.parse(jsonResult);
        obj.setCurrency(BlockchainConstants.BITCOIN.toUpperCase());
        return obj;
    }
}