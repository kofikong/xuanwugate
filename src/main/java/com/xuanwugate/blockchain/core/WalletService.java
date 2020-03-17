package com.xuanwugate.blockchain.core;

import com.xuanwugate.blockchain.common.EndpointConfig;

public abstract class WalletService extends BaseConstructor {
    public WalletService(EndpointConfig config){
        this.config = config;
    }
}