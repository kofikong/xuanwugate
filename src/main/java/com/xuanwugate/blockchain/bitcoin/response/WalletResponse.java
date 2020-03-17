package com.xuanwugate.blockchain.bitcoin.response;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.rpc.IPayload;

/**
 * WalletResponse
 */
public class WalletResponse implements IPayload  {

    public static WalletResponse parse(JSONObject jsonResult){
        WalletResponse obj = jsonResult.toJavaObject(WalletResponse.class);
		return obj;
	}
}