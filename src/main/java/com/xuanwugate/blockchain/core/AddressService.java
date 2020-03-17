package com.xuanwugate.blockchain.core;

import com.xuanwugate.blockchain.common.EndpointConfig;

public abstract class AddressService extends BaseConstructor {
    public AddressService(EndpointConfig config){
        this.config = config;
    }
}
