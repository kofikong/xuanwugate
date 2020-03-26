package com.xuanwugate.rpc;

/**
 * Payload
 */
public class PayloadResponse extends Response {
    public PayloadResponse(BaseResponse payload){
        this.payload = payload;
    }
    private BaseResponse payload;

	public BaseResponse getPayload() {
		return payload;
	}

	public void setPayload(BaseResponse payload) {
		this.payload = payload;
	}
    
}