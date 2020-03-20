package com.xuanwugate.rpc;

/**
 * Payload
 */
public class PayloadResponse extends Response {
    public PayloadResponse(IPayload payload){
        this.payload = payload;
    }
    private IPayload payload;

	public IPayload getPayload() {
		return payload;
	}

	public void setPayload(IPayload payload) {
		this.payload = payload;
	}
    
}