package com.xuanwugate.rpc;

public abstract class RPCResult extends BaseResponse {
    public void setError(RPCResult result){
        this.error = result!= null ? result.getError():null;
    }    
}