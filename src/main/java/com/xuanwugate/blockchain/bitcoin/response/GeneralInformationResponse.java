package com.xuanwugate.blockchain.bitcoin.response;

import com.alibaba.fastjson.JSONObject;

import com.xuanwugate.response.BaseResponse;

/**
 * GeneralInformation
 */
public class GeneralInformationResponse extends BaseResponse {
    private double difficulty;
    private long headers;
    private String chain;
    private String chainWork;
    private long mediantime;
    private long blocks;
    private String bestBlockHash;
    private String currency;
    private long transactions;
    private double verificationProgress;
    public GeneralInformationResponse(){}
    public GeneralInformationResponse(double difficulty,long headers,String chain,String chainWork,long mediantime,long blocks,String bestBlockHash,String currency,long transactions, double verificationProgress){
        this.difficulty = difficulty;
        this.headers = headers;
        this.chain = chain;
        this.chainWork = chainWork;
        this.mediantime = mediantime;
        this.blocks = blocks;
        this.bestBlockHash = bestBlockHash;
        this.currency = currency;
        this.transactions = transactions;
        this.verificationProgress = verificationProgress;
	}
	
	public static GeneralInformationResponse parse(JSONObject jsonResult){
		GeneralInformationResponse obj = jsonResult.toJavaObject(GeneralInformationResponse.class);
		return obj;
	}

	public double getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}

	public long getHeaders() {
		return headers;
	}

	public void setHeaders(long headers) {
		this.headers = headers;
	}

	public String getChain() {
		return chain;
	}

	public void setChain(String chain) {
		this.chain = chain;
	}

	public String getChainWork() {
		return chainWork;
	}

	public void setChainWork(String chainWork) {
		this.chainWork = chainWork;
	}

	public long getMediantime() {
		return mediantime;
	}

	public void setMediantime(long mediantime) {
		this.mediantime = mediantime;
	}

	public long getBlocks() {
		return blocks;
	}

	public void setBlocks(long blocks) {
		this.blocks = blocks;
	}

	public String getBestBlockHash() {
		return bestBlockHash;
	}

	public void setBestBlockHash(String bestBlockHash) {
		this.bestBlockHash = bestBlockHash;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getTransactions() {
		return transactions;
	}

	public void setTransactions(long transactions) {
		this.transactions = transactions;
	}

	public double getVerificationProgress() {
		return verificationProgress;
	}
	
	public void setVerificationProgress(double verificationProgress) {
		this.verificationProgress = verificationProgress;
	}
}