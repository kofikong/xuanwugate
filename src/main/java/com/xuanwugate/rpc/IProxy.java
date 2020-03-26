package com.xuanwugate.rpc;

import java.io.IOException;

/**
* IProxy
*/
public interface IProxy {
    RPCProxyResponse run(IRequestParams params) throws IOException;
}