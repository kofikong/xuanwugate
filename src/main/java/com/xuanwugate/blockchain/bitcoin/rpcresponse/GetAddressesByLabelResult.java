package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.rpc.RPCResult;
import com.xuanwugate.rpc.RPCSpecificResult;

/**
 * GetAddressesByLabelResult
 */
public class GetAddressesByLabelResult extends RPCResult  implements RPCSpecificResult{
    List<String> addresses;

	 

	@Override
	public void init(JSON json) {
        if(addresses == null){
            addresses = new ArrayList<>();
        }
		if(json instanceof JSONObject){
            JSONObject obj = (JSONObject)json;
            for(String key : obj.keySet()){
                addresses.add(key);
            }
        }
	}



	public List<String> getAddresses() {
		return addresses;
	}



	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}    
}