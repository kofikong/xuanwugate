package com.xuanwugate.rpc;

import com.xuanwugate.response.BaseResponse;

/**
 * RPCResult is rpc response data
 */
public abstract class RPCResult extends BaseResponse {
    public void setError(RPCResult result){
        this.error = result!= null ? result.getError():null;
    }
}