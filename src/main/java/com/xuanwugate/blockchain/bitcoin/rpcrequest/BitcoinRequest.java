/**
 * curl --user blockdaemon --data-binary '{"jsonrpc": "1.0", "id":"curltest", "method": "getblockchaininfo", "params": [] }' -H 'content-type: text/plain;' https://btccore-test.bdnodes.net?auth=4-k3zyRSDj2zEV1NO3m4BAOXYp1c4WdxqNWrJcuhxlk
 */
package com.xuanwugate.blockchain.bitcoin.rpcrequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.DatatypeConverter;

import com.xuanwugate.blockchain.config.BlockchainConfig;
import com.xuanwugate.rpc.IRequestParams;
import com.xuanwugate.rpc.JSONRPC;

import io.netty.util.internal.StringUtil;

/**
 * NodeInformationRequest
 */
public class BitcoinRequest implements IRequestParams {
    private JSONRPC jsonRPC;
    public BitcoinRequest(){
        jsonRPC = new JSONRPC();
    }

    public void setMethod(String method){
        jsonRPC.setMethod(method);
    }

    public List<Object> getParams(){
        return jsonRPC.getParams();
    }

	@Override
	public String getUri() {
        return BlockchainConfig.getInstance().getBitcoinRPCUri();
    }
    
    @Override
    public String getBody() {
        String tmp = jsonRPC.toString();
		return tmp;
    }

	@Override
	public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        String contentType = BlockchainConfig.getInstance().getBitcoinRPCRequestContentType();
        if(!StringUtil.isNullOrEmpty(contentType)){
            headers.put("Content-Type",contentType);
        }
        
        String authBaseic = BlockchainConfig.getInstance().getBitcoinRPCRequestAuthorizationBasic();
        if(!StringUtil.isNullOrEmpty(authBaseic) && !"none".equals(authBaseic)){
			try {
                String encoding = DatatypeConverter.printBase64Binary(authBaseic.getBytes("UTF-8"));
                headers.put("Authorization", "Basic " + encoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        }
        
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        
		return headers;
    }
    
    @Override
    public String toString() {
        return jsonRPC.toString();
    }

	@Override
	public JSONRPC getJSONRPC() {
		return jsonRPC;
	}
}
