package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xuanwugate.rpc.RPCResult;
import com.xuanwugate.rpc.RPCSpecificResult;

/**
 * ListLabels
 */
public class ListLabelsResult extends RPCResult  implements RPCSpecificResult{
    List<String> labels;

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	@Override
	public void init(JSON json) {
        if(labels == null){
            labels = new ArrayList<>();
        }
		if(json instanceof JSONArray){
            JSONArray array = (JSONArray)json;
            for(int index =0; index < array.size(); index++){
                labels.add(array.getString(index));
            }
        }
	}    
}