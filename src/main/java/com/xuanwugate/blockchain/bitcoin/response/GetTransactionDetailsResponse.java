package com.xuanwugate.blockchain.bitcoin.response;

import com.alibaba.fastjson.JSONArray;
import com.xuanwugate.blockchain.bitcoin.rpcresponse.GetRawTransactionResult;
import com.xuanwugate.response.BaseResponse;

/**
 * GetTransactionDetailsResponse
 */
public class GetTransactionDetailsResponse extends BaseResponse{
	private String txid;
	private String hash;
	private int index;
	private int version;
	private int size;
	private int vsize;
	private int locktime;
	private String time;
	private String blockhash;
	private int blockheight;
	private String blocktime;
	private int timestamp;
	private int confirmations;
	private JSONArray txins;
	private JSONArray txouts;

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
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
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
	public int getLocktime() {
		return locktime;
	}
	public void setLocktime(int locktime) {
		this.locktime = locktime;
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
	public int getBlockheight() {
		return blockheight;
	}
	public void setBlockheight(int blockheight) {
		this.blockheight = blockheight;
	}
	public String getBlocktime() {
		return blocktime;
	}
	public void setBlocktime(String blocktime) {
		this.blocktime = blocktime;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	public int getConfirmations() {
		return confirmations;
	}
	public void setConfirmations(int confirmations) {
		this.confirmations = confirmations;
	}
	public JSONArray getTxins() {
		return txins;
	}
	public void setTxins(JSONArray txins) {
		this.txins = txins;
	}
	public JSONArray getTxouts() {
		return txouts;
	}
	public void setTxouts(JSONArray txouts) {
		this.txouts = txouts;
	}
	public void init(GetRawTransactionResult raw) {
		//Different
		this.setBlockheight(0);

		this.setError(raw.getError());
		this.setBlockhash(raw.getBlockhash());
		this.setBlocktime(raw.getBlocktime());
		this.setConfirmations(raw.getConfirmations());
		this.setHash(raw.getHash());
		this.setLocktime(raw.getLocktime());
		this.setSize(raw.getSize());
		this.setTime(raw.getTime());
		this.setTxid(raw.getTxid());
		this.setVersion(raw.getVersion());

		//Different
		this.setTxins(raw.getVin());

		//Different
		this.setTxouts(raw.getVout());
		
		this.setVsize(raw.getVsize());
	}
}

/**
{
  "payload": {
      "txid": "5a4ebf66822b0b2d56bd9dc64ece0bc38ee7844a23ff1d7320a88c5fdb2ad3e2",
      "hash": "5a4ebf66822b0b2d56bd9dc64ece0bc38ee7844a23ff1d7320a88c5fdb2ad3e2",
      "index": 1,
      "version": 1,
      "size": 158,
      "vsize": 158,
      "locktime": 0,
      "time": "2010-09-16 05:03:47 UTC",
      "blockhash": "000000000043a8c0fd1d6f726790caa2a406010d19efd2780db27bdbbd93baf6",
      "blockheight": 80000,
      "blocktime": "2010-09-16 05:03:47 UTC",
      "timestamp": 1284613427,
      "confirmations": 484349,
      "txins": [
          {
              "txout": "f5d8ee39a430901c91a5917b9f2dc19d6d1a0e9cea205b009ca73dd04470b9a6",
              "vout": 0,
              "amount": "50.00000000",
              "addresses": [
                  "1JBSCVF6VM6QjFZyTnbpLjoCJTQEqVbepG"
              ],
              "script": {
                  "asm": "304502206e21798a42fae0e854281abd38bacd1aeed3ee3738d9e1446618c4571d1090db022100e2ac980643b0b82c0e88ffdfec6b64e3e6ba35e7ba5fdd7d5d6cc8d25c6b2415[ALL]",
                  "hex": "48304502206e21798a42fae0e854281abd38bacd1aeed3ee3738d9e1446618c4571d1090db022100e2ac980643b0b82c0e88ffdfec6b64e3e6ba35e7ba5fdd7d5d6cc8d25c6b241501"
              },
              "votype": "pubkey"
          }
      ],
      "txouts": [
          {
              "amount": "50.00000000",
              "type": "pubkeyhash",
              "spent": true,
              "addresses": [
                  "16ro3Jptwo4asSevZnsRX6vfRS24TGE6uK"
              ],
              "script": {
                  "asm": "OP_DUP OP_HASH160 404371705fa9bd789a2fcd52d2c580b65d35549d OP_EQUALVERIFY OP_CHECKSIG",
                  "hex": "76a914404371705fa9bd789a2fcd52d2c580b65d35549d88ac",
                  "reqsigs": 1
              }
          }
      ]
  }
}
*/