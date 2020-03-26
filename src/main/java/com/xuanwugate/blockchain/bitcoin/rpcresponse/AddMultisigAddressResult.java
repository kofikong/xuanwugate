package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import com.xuanwugate.rpc.RPCResult;

/**
 * AddMultisigAddressResult
 */
public class AddMultisigAddressResult extends RPCResult {
    private String address;
    private String redeemScript;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRedeemScript() {
		return redeemScript;
	}

	public void setRedeemScript(String redeemScript) {
		this.redeemScript = redeemScript;
	}

    
}