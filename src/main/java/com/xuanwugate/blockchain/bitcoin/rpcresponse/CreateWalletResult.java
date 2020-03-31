package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import java.util.Date;

import com.xuanwugate.rpc.RPCResult;

/**
 * CreateWalletResult
 */
public class CreateWalletResult extends RPCResult {
	public CreateWalletResult(){
		datetime = new Date(System.currentTimeMillis()).toString();
	}

	private String datetime;
    private String name;
    private String warning;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

    
}