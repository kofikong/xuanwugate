
package com.xuanwugate.rpc;

import java.util.Map;

/**
 * IRequestParam
 */
public interface IRequestParams {

	String getUri();
	String getBody();
	JSONRPC getJSONRPC();

	Map<String, String> getHeaders();
}