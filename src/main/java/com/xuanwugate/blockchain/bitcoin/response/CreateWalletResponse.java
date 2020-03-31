package com.xuanwugate.blockchain.bitcoin.response;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.response.BaseResponse;

/**
 * WalletResponse
 */
public class CreateWalletResponse extends BaseResponse  {
    private List<String> addresses;
    private String walletName;
    
    public static CreateWalletResponse parse(JSONObject jsonResult){
        CreateWalletResponse obj = jsonResult.toJavaObject(CreateWalletResponse.class);
		return obj;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}
}