package com.xuanwugate.blockchain.core;

import com.xuanwugate.blockchain.common.EndpointConfig;

public abstract class HDWalletService extends BaseConstructor {
    public HDWalletService(EndpointConfig config){
        this.config = config;
    }
}