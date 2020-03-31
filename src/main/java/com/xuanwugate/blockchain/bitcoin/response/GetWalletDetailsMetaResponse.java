package com.xuanwugate.blockchain.bitcoin.response;

import java.util.List;
import java.util.Map;

import com.xuanwugate.response.BaseResponse;

/**
 * GetWalletDetailsMetaResponse
 */
public class GetWalletDetailsMetaResponse extends BaseResponse{
	private int totalCount;
	private int limit;
	private int results;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getResults() {
		return results;
	}
	public void setResults(int results) {
		this.results = results;
	}
	
}

/**
	{
		"payload": {
			"walletName": "demowallet",
			"addresses": [
				{
					"address": "mtFYoSowT3i649wnBDYjCjewenh8AuofQb",
					"balance": "0.00005238"
				}
			],
			"totalBalance": "0.00005238"
		},
		"meta": {
			"totalCount": 1,
			"limit": 50,
			"results": 1
		}
	}
	 */