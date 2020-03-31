package com.xuanwugate.response;

/**
 * BaseResponse
 */
public abstract class BaseResponse implements IResponse {
    protected ErrorInfo error;

    public void setError(ErrorInfo error){
        this.error = error;
    }
    
    public ErrorInfo getError(){
        return this.error;
    }

    public boolean isError(){
        return this.error != null;
    }
}