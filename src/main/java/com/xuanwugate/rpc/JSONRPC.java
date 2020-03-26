
package com.xuanwugate.rpc;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * JSONRPC
 */
public final class JSONRPC {
    private String jsonrpc = "2.0";
    private Object id = 1;
    private String method = "";
    private List<Object> params = new ArrayList<>();

	@Override
	public String toString() {
        JSONObject obj = (JSONObject)JSONObject.toJSON(this);
        String tmp =  obj.toJSONString();
        return tmp;
	}
    //{"jsonrpc": "1.0", "id":"curltest", "method": "getblockchaininfo", "params": [] }
    //{"method":"","params":[],"id":1,"jsonrpc":"2.0"}";

	public String getJsonrpc() {
		return jsonrpc;
	}

	public void setJsonrpc(String ver) {
		jsonrpc =  ver;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Object> getParams() {
		return params;
	}
}