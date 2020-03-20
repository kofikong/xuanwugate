package com.xuanwugate.rpc;

/**
 * Payload
 */
public abstract class Response {

    public static Response build(IPayload load){
        if(load == null){
            return new ErrorResponse(ErrorInfo.GeneralError("no data"));
        }
        
        ErrorInfo info = load.getError();
        if(info == null){
            return new PayloadResponse(load);
        }
        else{
            return new ErrorResponse(info);
        }
    }

    public static Response error(ErrorInfo info){
        return new ErrorResponse(info);
    }
}