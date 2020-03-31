package com.xuanwugate.blockchain.bitcoin.response;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xuanwugate.response.BaseResponse;
import com.xuanwugate.response.ISimpleResponse;
import com.xuanwugate.rpc.RPCSpecificResult;

/**
 * ListNormalWallet
 */
public class ListNormalWalletResponse  extends BaseResponse implements RPCSpecificResult,ISimpleResponse <String>{
    private List<String> payload;

	public List<String> getPayload() {
		return payload;
	}

	public void setPayload(List<String> payload) {
		this.payload = payload;
	}

	@Override
	public void init(JSON json) {
		if(json instanceof JSONArray){
			JSONArray array = (JSONArray)json;
			if(payload == null){
				payload = new ArrayList<String>();
			}
			
            for(int index =0; index < array.size(); index++){
                payload.add(array.getString(index));
            }
		}		
	}
}