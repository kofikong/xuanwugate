package com.xuanwugate.rpc;

/**
 * Payload
 */
public class Response {
    private Response(IPayload payload,int errorCode){
        this.payload = payload;
        this.errorCode = errorCode;
    }
    
    public static Response create(IPayload payload){
        if(payload != null){
            return new Response(payload,0);
        }
        else{
            return new Response(new EmptyPayload(),-1);
        }
    }

    public static Response error(int errorCode){
        return new Response(new EmptyPayload(),-1);
    }
    
    private IPayload payload;
    private int errorCode = 0;
    
	public IPayload getPayload() {
		return payload;
	}

	public void setPayload(IPayload payload) {
		this.payload = payload;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}