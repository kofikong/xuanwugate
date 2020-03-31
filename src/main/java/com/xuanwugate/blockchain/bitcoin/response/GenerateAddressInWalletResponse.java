package com.xuanwugate.blockchain.bitcoin.response;

import com.xuanwugate.response.BaseResponse;

/**
 * GenerateAddressInWalletResponse
 */
public class GenerateAddressInWalletResponse extends BaseResponse  {
    private CreateWalletResponse wallet_info;
	private GenerateAddressResponse address_info;
	
	public CreateWalletResponse getWallet_info() {
		return wallet_info;
	}
	public void setWallet_info(CreateWalletResponse wallet_info) {
		this.wallet_info = wallet_info;
	}
	public GenerateAddressResponse getAddress_info() {
		return address_info;
	}
	public void setAddress_info(GenerateAddressResponse address_info) {
		this.address_info = address_info;
	}
}