package com.xuanwugate.blockchain.bitcoin.response;

import com.xuanwugate.response.BaseResponse;

/**
 * ListNormalWalletMetaResponse
 */
public class ListNormalWalletMetaResponse extends BaseResponse{
    private int totalCount;
    private int results;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getResults() {
		return results;
	}
	public void setResults(int results) {
		this.results = results;
	}
    
}