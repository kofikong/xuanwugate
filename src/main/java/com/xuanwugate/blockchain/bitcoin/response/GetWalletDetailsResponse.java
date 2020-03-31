package com.xuanwugate.blockchain.bitcoin.response;

import java.util.List;
import java.util.Map;

import com.xuanwugate.response.BaseResponse;

/**
 * GetWalletDetailsResponse
 */
public class GetWalletDetailsResponse extends BaseResponse{
	private String walletName;
	private List<Map<String,String>> addresses;
	private String totalBalance;
	
	public String getWalletName() {
		return walletName;
	}
	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}
	public List<Map<String, String>> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Map<String, String>> addresses) {
		this.addresses = addresses;
	}
	public String getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
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