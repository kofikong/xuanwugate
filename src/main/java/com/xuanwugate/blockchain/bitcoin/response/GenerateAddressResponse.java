package com.xuanwugate.blockchain.bitcoin.response;

import com.xuanwugate.rpc.IPayload;

/**
 * AddressResponse
 */
public class GenerateAddressResponse  implements IPayload {
	private String address;
	private String privateKey;
	private String publicKey;
	private String wif;

    public static GenerateAddressResponse parse(String address,String privateKey,String publicKey, String wif){
        GenerateAddressResponse obj = new GenerateAddressResponse();
		obj.address = address;
		obj.privateKey = privateKey;
		obj.publicKey = publicKey;
		obj.wif = wif;
		return obj;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	public String getWif() {
		return wif;
	}
	public void setWif(String wif) {
		this.wif = wif;
	}
}