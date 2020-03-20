package com.xuanwugate.blockchain.bitcoin.response;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.rpc.ErrorInfo;
import com.xuanwugate.rpc.IPayload;

/**
 * AddressResponse
 */
public class AddressDetailsResponse  implements IPayload {
	private String address;
	private String totalSpent;
	private String totalReceived;
	private String balance;
	private int txi;
	private int txo;
	private int txsCount;
	private List<String> addresses;
	  
    public static AddressDetailsResponse parse(JSONObject jsonResult){
        AddressDetailsResponse obj = jsonResult.toJavaObject(AddressDetailsResponse.class);
		return obj;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTotalSpent() {
		return totalSpent;
	}


	public void setTotalSpent(String totalSpent) {
		this.totalSpent = totalSpent;
	}


	public String getTotalReceived() {
		return totalReceived;
	}


	public void setTotalReceived(String totalReceived) {
		this.totalReceived = totalReceived;
	}


	public String getBalance() {
		return balance;
	}


	public void setBalance(String balance) {
		this.balance = balance;
	}


	public int getTxi() {
		return txi;
	}


	public void setTxi(int txi) {
		this.txi = txi;
	}


	public int getTxo() {
		return txo;
	}


	public void setTxo(int txo) {
		this.txo = txo;
	}


	public int getTxsCount() {
		return txsCount;
	}


	public void setTxsCount(int txsCount) {
		this.txsCount = txsCount;
	}


	public List<String> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	@Override
	public void setError(ErrorInfo error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ErrorInfo getError() {
		// TODO Auto-generated method stub
		return null;
	}
}