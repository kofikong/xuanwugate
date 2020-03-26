package com.xuanwugate.blockchain.core;

import com.xuanwugate.blockchain.common.EndpointConfig;

public abstract class TransactionService extends BaseConstructor {
    public TransactionService(EndpointConfig config){
        this.config = config;
    }
}
