package com.xuanwugate.rpc;

import com.alibaba.fastjson.JSON;

/**
 * RPCSpecificResult
 */
public interface RPCSpecificResult {
    void init(JSON json);
}