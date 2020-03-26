package com.xuanwugate.rpc;

/**
 * RPCProxyResponse
 */
public class RPCProxyResponse {
	
	public static RPCProxyResponse create(int responseCode, String message){
		RPCProxyResponse obj = new RPCProxyResponse();
		obj.responseCode = responseCode;
		obj.message = message;
		return obj;
	}
    
    private int responseCode;
    private String message;
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public boolean valid(){
		return responseCode >=100 && responseCode < 400;
	}
}