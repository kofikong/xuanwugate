package com.xuanwugate.rpc;

/**
 * ErrorInfo
 */
public class ErrorInfo {
    public ErrorInfo(){
        
    }
    protected ErrorInfo(int code, String message){
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    public static ErrorInfo GeneralError(String message){
        return new ErrorInfo(1,String.format("General error: %s", message));
    }

    public static ErrorInfo BlockchainConnectionError(String message){
        return new ErrorInfo(31,String.format("Blockchain connection error: %s", message));
    }
}