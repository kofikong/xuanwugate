package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import java.math.BigDecimal;
import java.util.Map;

import com.xuanwugate.rpc.RPCResult;

/**
 * GetBalancesResult
 */
public class GetBalancesResult extends RPCResult{
	private Map<String,BigDecimal> mine;

	public Map<String, BigDecimal> getMine() {
		return mine;
	}

	public void setMine(Map<String, BigDecimal> mine) {
		this.mine = mine;
	}

	public BigDecimal getTrusted() {
		if(this.mine != null){
			return this.mine.get("trusted");
		}
		
		return BigDecimal.ZERO;
	}

	public BigDecimal getUntrustedPending() {
		if(this.mine != null){
			return this.mine.get("untrusted_pending");
		}
		
		return BigDecimal.ZERO;
	}

	public BigDecimal getImmature() {
		if(this.mine != null){
			return this.mine.get("immature");
		}
		
		return BigDecimal.ZERO;
	}

	/**
	{
		"result": {
			"mine": {
				"trusted": 0.00050000,
				"untrusted_pending": 0.00000000,
				"immature": 0.00000000
			}
		},
		"error": null,
		"id": "koftest3"
	}
	 */
	
}