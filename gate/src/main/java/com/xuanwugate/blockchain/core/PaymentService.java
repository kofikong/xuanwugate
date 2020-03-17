package com.xuanwugate.blockchain.core;

import com.xuanwugate.blockchain.common.EndpointConfig;

public abstract class PaymentService extends BaseConstructor {
    public PaymentService(EndpointConfig config){
        this.config = config;
    }
}