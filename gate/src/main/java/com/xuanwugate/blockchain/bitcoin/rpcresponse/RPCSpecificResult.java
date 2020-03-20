package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import com.alibaba.fastjson.JSON;

/**
 * RPCSpecificResult
 */
public interface RPCSpecificResult {
    void init(JSON json);
}