package com.xuanwugate.blockchain.core;

import com.xuanwugate.blockchain.common.EndpointConfig;

public abstract class BlockchainService extends BaseConstructor {
    public BlockchainService(EndpointConfig config){
        this.config = config;
    }
}
