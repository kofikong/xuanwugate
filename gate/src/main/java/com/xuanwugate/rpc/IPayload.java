package com.xuanwugate.rpc;

/**
 * IPayload
 */
public interface IPayload {
    void setError(ErrorInfo error);
    ErrorInfo getError();   
}