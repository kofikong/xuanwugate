package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import java.math.BigDecimal;

import com.xuanwugate.rpc.RPCResult;

/**
 * WalletInfo
 */
public class WalletInfo extends RPCResult {
    private String walletname;
    private int walletversion;
    private BigDecimal balance;
    private BigDecimal unconfirmed_balance;
    private BigDecimal immature_balance;
    private int txcount;
    private int keypoololdest;
    private int keypoolsize;
    private int keypoolsize_hd_internal;
    private BigDecimal paytxfee;
    private String hdseedid;
    private boolean private_keys_enabled;
    private boolean avoid_reuse;
    private boolean scanning;
	public String getWalletname() {
		return walletname;
	}
	public void setWalletname(String walletname) {
		this.walletname = walletname;
	}
	public int getWalletversion() {
		return walletversion;
	}
	public void setWalletversion(int walletversion) {
		this.walletversion = walletversion;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getUnconfirmed_balance() {
		return unconfirmed_balance;
	}
	public void setUnconfirmed_balance(BigDecimal unconfirmed_balance) {
		this.unconfirmed_balance = unconfirmed_balance;
	}
	public BigDecimal getImmature_balance() {
		return immature_balance;
	}
	public void setImmature_balance(BigDecimal immature_balance) {
		this.immature_balance = immature_balance;
	}
	public int getTxcount() {
		return txcount;
	}
	public void setTxcount(int txcount) {
		this.txcount = txcount;
	}
	public int getKeypoololdest() {
		return keypoololdest;
	}
	public void setKeypoololdest(int keypoololdest) {
		this.keypoololdest = keypoololdest;
	}
	public int getKeypoolsize() {
		return keypoolsize;
	}
	public void setKeypoolsize(int keypoolsize) {
		this.keypoolsize = keypoolsize;
	}
	public int getKeypoolsize_hd_internal() {
		return keypoolsize_hd_internal;
	}
	public void setKeypoolsize_hd_internal(int keypoolsize_hd_internal) {
		this.keypoolsize_hd_internal = keypoolsize_hd_internal;
	}
	public BigDecimal getPaytxfee() {
		return paytxfee;
	}
	public void setPaytxfee(BigDecimal paytxfee) {
		this.paytxfee = paytxfee;
	}
	public String getHdseedid() {
		return hdseedid;
	}
	public void setHdseedid(String hdseedid) {
		this.hdseedid = hdseedid;
	}
	public boolean isPrivate_keys_enabled() {
		return private_keys_enabled;
	}
	public void setPrivate_keys_enabled(boolean private_keys_enabled) {
		this.private_keys_enabled = private_keys_enabled;
	}
	public boolean isAvoid_reuse() {
		return avoid_reuse;
	}
	public void setAvoid_reuse(boolean avoid_reuse) {
		this.avoid_reuse = avoid_reuse;
	}
	public boolean isScanning() {
		return scanning;
	}
	public void setScanning(boolean scanning) {
		this.scanning = scanning;
	}    
}

/**
 *      "walletname": "",
        "walletversion": 169900,
        "balance": 0.00000000,
        "unconfirmed_balance": 0.00000000,
        "immature_balance": 0.00000000,
        "txcount": 0,
        "keypoololdest": 1584187475,
        "keypoolsize": 999,
        "keypoolsize_hd_internal": 1000,
        "paytxfee": 0.00000000,
        "hdseedid": "65d5ae7a05335342aa37595539441303dd7f09a7",
        "private_keys_enabled": true,
        "avoid_reuse": false,
        "scanning": false
 */