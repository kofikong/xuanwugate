package com.xuanwugate.blockchain.core;

import java.lang.reflect.Constructor;

import com.xuanwugate.blockchain.common.EndpointConfig;

/**
 * BaseConstructor
 */
public class BaseConstructor {
    protected EndpointConfig config;
    
    protected <T> Constructor<T> getConstructor(final Class<T> clazz) throws Exception {
        Constructor<T> declaredConstructor = clazz.getDeclaredConstructor(EndpointConfig.class);
        declaredConstructor.setAccessible(true);
        return declaredConstructor;
    }
}