package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import java.util.List;
import java.util.Map;

/**
 * AddressInfo
 */
public class AddressInfo extends RPCResult{
    private String address; 
    private String scriptPubKey; 
    private boolean ismine; 
    private boolean solvable; 
    private String desc; 
    private boolean iswatchonly; 
    private boolean isscript; 
    private boolean iswitness; 
    private String script; 
    private String hex; 
    private String pubkey; 
    private Map<String,Object> embedded; 
    private String label; 
    private boolean ischange; 
    private int timestamp; 
    private String hdkeypath; 
    private String hdseedid; 
    private String hdmasterfingerprint; 
    private List<Map<String,Object>> labels;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getScriptPubKey() {
		return scriptPubKey;
	}
	public void setScriptPubKey(String scriptPubKey) {
		this.scriptPubKey = scriptPubKey;
	}
	public boolean isIsmine() {
		return ismine;
	}
	public void setIsmine(boolean ismine) {
		this.ismine = ismine;
	}
	public boolean isSolvable() {
		return solvable;
	}
	public void setSolvable(boolean solvable) {
		this.solvable = solvable;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isIswatchonly() {
		return iswatchonly;
	}
	public void setIswatchonly(boolean iswatchonly) {
		this.iswatchonly = iswatchonly;
	}
	public boolean isIsscript() {
		return isscript;
	}
	public void setIsscript(boolean isscript) {
		this.isscript = isscript;
	}
	public boolean isIswitness() {
		return iswitness;
	}
	public void setIswitness(boolean iswitness) {
		this.iswitness = iswitness;
	}
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String getHex() {
		return hex;
	}
	public void setHex(String hex) {
		this.hex = hex;
	}
	public String getPubkey() {
		return pubkey;
	}
	public void setPubkey(String pubkey) {
		this.pubkey = pubkey;
	}
	public Map<String,Object> getEmbedded() {
		return embedded;
	}

	public void setEmbedded(Map<String,Object> embedded) {
		this.embedded = embedded;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isIschange() {
		return ischange;
	}
	public void setIschange(boolean ischange) {
		this.ischange = ischange;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	public String getHdkeypath() {
		return hdkeypath;
	}
	public void setHdkeypath(String hdkeypath) {
		this.hdkeypath = hdkeypath;
	}
	public String getHdseedid() {
		return hdseedid;
	}
	public void setHdseedid(String hdseedid) {
		this.hdseedid = hdseedid;
	}
	public String getHdmasterfingerprint() {
		return hdmasterfingerprint;
	}
	public void setHdmasterfingerprint(String hdmasterfingerprint) {
		this.hdmasterfingerprint = hdmasterfingerprint;
	}

	public List<Map<String,Object>> getLabels() {
		return  labels;
	}

	public void setLabels(List<Map<String,Object>> labels) {
		this.labels= labels;
	}
}

/**
 *      "address": "2MtxGty81MZvSB5qEvhPpvUfypXU9NGsQFq",
        "scriptPubKey": "a91412bb9ba516fcec36b51af54fa0721b0310774f0f87",
        "ismine": true,
        "solvable": true,
        "desc": "sh(wpkh([acd450e2/0'/0'/1']023ff97308dc14f406ddeac67770752986f1b61000a97e91762fa8fd7cc8130958))#ta7sl746",
        "iswatchonly": false,
        "isscript": true,
        "iswitness": false,
        "script": "witness_v0_keyhash",
        "hex": "0014ce03c8e09c473ec821fffcc11496c4ef92f0e782",
				#publicKey
        "pubkey": "023ff97308dc14f406ddeac67770752986f1b61000a97e91762fa8fd7cc8130958",
        "embedded": {
            "isscript": false,
            "iswitness": true,
            "witness_version": 0,
            "witness_program": "ce03c8e09c473ec821fffcc11496c4ef92f0e782",
            "pubkey": "023ff97308dc14f406ddeac67770752986f1b61000a97e91762fa8fd7cc8130958",
            "address": "tb1qecpu3cyugulvsg0llnq3f9kya7f0peuznz4cf4",
            "scriptPubKey": "0014ce03c8e09c473ec821fffcc11496c4ef92f0e782"
        },
        "label": "",
        "ischange": false,
        "timestamp": 1584262448,
        "hdkeypath": "m/0'/0'/1'",
        "hdseedid": "80b771b045229e8c05729ced15432837398445c6",
        "hdmasterfingerprint": "acd450e2",
        "labels": [
            {
                "name": "",
                "purpose": "receive"
            }
        ]
 * 
 */

 