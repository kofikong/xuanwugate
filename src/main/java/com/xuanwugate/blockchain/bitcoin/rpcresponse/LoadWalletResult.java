package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import com.xuanwugate.rpc.RPCResult;

/**
 * LoadWalletResult
 */
public class LoadWalletResult extends RPCResult {
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

/**
 {
    "result": {
        "name": "koftest4",
        "warning": ""
    },
    "error": null,
    "id": "koftest3"
}
 */