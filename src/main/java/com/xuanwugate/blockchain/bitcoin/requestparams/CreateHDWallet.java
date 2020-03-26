package com.xuanwugate.blockchain.bitcoin.requestparams;

import javax.ws.rs.QueryParam;

/**
 * CreateWallet
 */
public class CreateHDWallet {
	@QueryParam("walletName")
	private String walletName;
	@QueryParam("addressCount")
	private int addressCount;
	@QueryParam("password")
	private String password;
	
	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	 
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAddressCount() {
		return addressCount;
	}

	public void setAddressCount(int addressCount) {
		this.addressCount = addressCount;
	}

	public boolean valid() {
		return walletName != null && addressCount >0 && password != null;
	}
}