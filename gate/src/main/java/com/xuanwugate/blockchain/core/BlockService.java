package com.xuanwugate.blockchain.core;

import com.xuanwugate.blockchain.common.EndpointConfig;

public abstract class BlockService extends BaseConstructor {
    public BlockService(EndpointConfig config){
        this.config = config;
    }
}
