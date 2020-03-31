package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import com.alibaba.fastjson.JSONArray;
import com.xuanwugate.rpc.RPCResult;

/**
 * GetRawTransactionResult
 */
public class GetRawTransactionResult extends RPCResult{
	private String txid;
	private String hash;
	private int version;
	private int size;
	private int vsize;
	private int weight;
	private int locktime;
	private String hex;
	private String time;
	private String blockhash;
	private String blocktime;
	private int confirmations;
	private JSONArray vin;
	private JSONArray vout;

	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getVsize() {
		return vsize;
	}
	public void setVsize(int vsize) {
		this.vsize = vsize;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getLocktime() {
		return locktime;
	}
	public void setLocktime(int locktime) {
		this.locktime = locktime;
	}
	public String getHex() {
		return hex;
	}
	public void setHex(String hex) {
		this.hex = hex;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBlockhash() {
		return blockhash;
	}
	public void setBlockhash(String blockhash) {
		this.blockhash = blockhash;
	}
	public String getBlocktime() {
		return blocktime;
	}
	public void setBlocktime(String blocktime) {
		this.blocktime = blocktime;
	}
	public int getConfirmations() {
		return confirmations;
	}
	public void setConfirmations(int confirmations) {
		this.confirmations = confirmations;
	}
	public JSONArray getVin() {
		return vin;
	}
	public void setVin(JSONArray vin) {
		this.vin = vin;
	}
	public JSONArray getVout() {
		return vout;
	}
	public void setVout(JSONArray vout) {
		this.vout = vout;
	}
	
	
}

/**
{
    "result": {
        "txid": "4c6197a86f1b393a4d3a98b258047a9af4a609daec0604e9360b36d810364c28",
        "hash": "08116209f9f67a32673e69fee1c9eddea3fb6eb26c304adf5ee2df530edf4006",
        "version": 2,
        "size": 249,
        "vsize": 168,
        "weight": 669,
		"locktime": 1670189,
		"hex": "02000000000101c1ec4351b4b8e0dbcf6b93ea0183fedabaee8156091a8667fa169fb119808ebd0100000017160014ffae4136cccfe9b0835ec0d74519bd656d684f04feffffff02282840000000000017a9140d7ad854b27fb75e762b1c6be13a28579a00569b8730750000000000001976a9140f91bfb99c39494686456814cb76b042466adae388ac0247304402200e467f7b0e81012ef0453132a3a46f6c3d4a30deae7d0e67d9098471a2d33043022051c05858a8ffed59cab433a87ad9c96d7745fa73ac8ff2c388f639928e357d0e0121034fd1fb7c43c8440a0c7dd11100c8075681ed2b11bce19f20d7bcdc483f6f5e792d7c1900",
        "blockhash": "00000000000001023aeb2362a35ed57d4230113ba5c5b2605e4f0f36cca627bd",
        "confirmations": 22407,
        "time": 1584453894,
        "blocktime": 1584453894
        "vin": [
            {
                "txid": "bd8e8019b19f16fa67861a095681eebadafe8301ea936bcfdbe0b8b45143ecc1",
                "vout": 1,
                "scriptSig": {
                    "asm": "0014ffae4136cccfe9b0835ec0d74519bd656d684f04",
                    "hex": "160014ffae4136cccfe9b0835ec0d74519bd656d684f04"
                },
                "txinwitness": [
                    "304402200e467f7b0e81012ef0453132a3a46f6c3d4a30deae7d0e67d9098471a2d33043022051c05858a8ffed59cab433a87ad9c96d7745fa73ac8ff2c388f639928e357d0e01",
                    "034fd1fb7c43c8440a0c7dd11100c8075681ed2b11bce19f20d7bcdc483f6f5e79"
                ],
                "sequence": 4294967294
            }
        ],
        "vout": [
            {
                "value": 0.04204584,
                "n": 0,
                "scriptPubKey": {
                    "asm": "OP_HASH160 0d7ad854b27fb75e762b1c6be13a28579a00569b OP_EQUAL",
                    "hex": "a9140d7ad854b27fb75e762b1c6be13a28579a00569b87",
                    "reqSigs": 1,
                    "type": "scripthash",
                    "addresses": [
                        "2MtUVwCcBAAo7pyoUzDVbZDo5nwNNUtupvq"
                    ]
                }
            },
            {
                "value": 0.00030000,
                "n": 1,
                "scriptPubKey": {
                    "asm": "OP_DUP OP_HASH160 0f91bfb99c39494686456814cb76b042466adae3 OP_EQUALVERIFY OP_CHECKSIG",
                    "hex": "76a9140f91bfb99c39494686456814cb76b042466adae388ac",
                    "reqSigs": 1,
                    "type": "pubkeyhash",
                    "addresses": [
                        "mgwH2k8NPHcX3ePXiaWEpkA5gtW6JhuDVD"
                    ]
                }
            }
        ]
    },
    "error": null,
    "id": "koftest4"
}
*/