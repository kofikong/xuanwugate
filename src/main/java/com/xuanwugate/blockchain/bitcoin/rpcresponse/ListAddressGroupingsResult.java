package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xuanwugate.rpc.RPCResult;
import com.xuanwugate.rpc.RPCSpecificResult;

/**
 * ListLabels
 */
public class ListAddressGroupingsResult extends RPCResult  implements RPCSpecificResult{
	List<Map<String,String>> balancesGroup;

	@Override
	public void init(JSON json) {
        if(balancesGroup == null){
            balancesGroup = new ArrayList<Map<String,String>>();
		}
		
		if(json instanceof JSONArray){
            JSONArray array = (JSONArray)json;
            for(int index =0; index < array.size(); index++){
                JSONArray item = array.getJSONArray(index);
                if(item.size() > 0){
                    JSONArray subItem = item.getJSONArray(0);
                    HashMap<String,String> data = new HashMap<String,String>();
                    data.put("address", subItem.getString(0));
                    data.put("balance", subItem.getString(1));
                    balancesGroup.add(data);
                }
            }
        }
	}

	public List<Map<String, String>> getBalancesGroup() {
		return balancesGroup;
	}

	public void setBalancesGroup(List<Map<String, String>> balancesGroup) {
		this.balancesGroup = balancesGroup;
	}    
}
/**
 {
    "result": [
        [
            [
                "mgwH2k8NPHcX3ePXiaWEpkA5gtW6JhuDVD",
                0.00050000,
                "testnetDVD"
            ]
        ],
        [
            [
                "mmXp8auJNsfHm5DHt8o8HMt51SD7WTrTLJ",
                0.00000000,
                ""
            ]
        ]
    ],
    "error": null,
    "id": 1
}
 */