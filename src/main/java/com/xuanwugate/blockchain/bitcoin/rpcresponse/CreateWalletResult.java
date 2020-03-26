package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import com.xuanwugate.rpc.RPCResult;

/**
 * CreateWalletResult
 */
public class CreateWalletResult extends RPCResult {
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

    
}