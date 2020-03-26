package com.xuanwugate.rpc;

/**
 * BaseResponse
 */
public abstract class BaseResponse {
    protected ErrorInfo error;

    public void setError(ErrorInfo error){
        this.error = error;
    }

    public void setError(RPCResult result){
        this.error = result!= null ? result.getError():null;
    }
    
    public ErrorInfo getError(){
        return this.error;
    }

    public boolean isError(){
        return this.error != null;
    }
}