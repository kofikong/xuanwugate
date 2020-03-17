package com.xuanwugate.blockchain.core;

import com.xuanwugate.blockchain.common.EndpointConfig;

public abstract class WebhookService extends BaseConstructor {
    public WebhookService(EndpointConfig config){
        this.config = config;
    }
}