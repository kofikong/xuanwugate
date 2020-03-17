package com.xuanwugate.rpc;

import java.io.IOException;

/**
* IProxy
*/
public interface IProxy {
    String run(IRequestParams params) throws IOException;
}