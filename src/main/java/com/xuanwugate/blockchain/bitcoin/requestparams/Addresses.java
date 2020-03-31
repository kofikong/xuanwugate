package com.xuanwugate.blockchain.bitcoin.requestparams;

import java.util.List;
import javax.ws.rs.QueryParam;

/**
 * CreateWallet
 */
public class Addresses {
	@QueryParam("addresses")
	private List<String> addresses;

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public boolean valid() {
		return addresses != null && addresses.size() > 0;
	}
}