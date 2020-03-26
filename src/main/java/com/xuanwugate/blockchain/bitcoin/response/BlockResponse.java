package com.xuanwugate.blockchain.bitcoin.response;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.rpc.BaseResponse;

/**
 * GeneralInformation
 */
public class BlockResponse extends BaseResponse {
    private String hash;
    private long confirmations;
    private long strippedsize;
    private long size;
    private long weight;
    private long height;
    private int version;
    private String versionHex;
    private String merkleroot;
    private List<String> tx = new ArrayList<>();
    private int time;
    private int mediantime;
    private int nonce;
    private String bits;
    private int difficulty;
    private String chainwork;
    private int nTx;
    private String previousblockhash;
    private String nextblockhash;
    

    public BlockResponse(){}
   
	public static BlockResponse parse(JSONObject jsonResult){
        BlockResponse obj = jsonResult.toJavaObject(BlockResponse.class);
		return obj;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public long getConfirmations() {
		return confirmations;
	}

	public void setConfirmations(long confirmations) {
		this.confirmations = confirmations;
	}

	public long getStrippedsize() {
		return strippedsize;
	}

	public void setStrippedsize(long strippedsize) {
		this.strippedsize = strippedsize;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getVersionHex() {
		return versionHex;
	}

	public void setVersionHex(String versionHex) {
		this.versionHex = versionHex;
	}

	public String getMerkleroot() {
		return merkleroot;
	}

	public void setMerkleroot(String merkleroot) {
		this.merkleroot = merkleroot;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getMediantime() {
		return mediantime;
	}

	public void setMediantime(int mediantime) {
		this.mediantime = mediantime;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public String getBits() {
		return bits;
	}

	public void setBits(String bits) {
		this.bits = bits;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getChainwork() {
		return chainwork;
	}

	public void setChainwork(String chainwork) {
		this.chainwork = chainwork;
	}

	public int getnTx() {
		return nTx;
	}

	public void setnTx(int nTx) {
		this.nTx = nTx;
	}

	public String getPreviousblockhash() {
		return previousblockhash;
	}

	public void setPreviousblockhash(String previousblockhash) {
		this.previousblockhash = previousblockhash;
	}

	public String getNextblockhash() {
		return nextblockhash;
	}

	public void setNextblockhash(String nextblockhash) {
		this.nextblockhash = nextblockhash;
	}

	public void setTx(List<String> tx) {
		this.tx = tx;
	}

	public List<String> getTx() {
		return tx;
	}
}