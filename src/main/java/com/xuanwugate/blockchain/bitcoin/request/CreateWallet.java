package com.xuanwugate.blockchain.bitcoin.request;

import java.util.List;
import javax.ws.rs.QueryParam;

/**
 * CreateWallet
 */
public class CreateWallet {
	@QueryParam("walletName")
	private String walletName;
	@QueryParam("addresses")
	private List<String> addresses;
	
	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public boolean valid() {
		return walletName != null && addresses != null && addresses.size() > 0;
	}
}