package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import com.alibaba.fastjson.JSONObject;

public interface RPCResult {
    static <T> T parse(final Class<T> clazz,final String jsonStr){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        return parse(clazz,jsonObject);
    }
    static <T> T parse(final Class<T> clazz,final JSONObject jsonObject){
        if(jsonObject == null){
            return null;
        }

        final String jsonError = jsonObject.getString("error");
        final Object result = jsonObject.get("result");

        if(jsonError != null || result == null){
            return null;
        }

        if(String.class.equals(clazz)){
            return (T)result;
        }
        else {
            // Class<?>[] interfaces = clazz.getInterfaces();
            // boolean isRPCResult = false;
            // for(Class<?> i : interfaces){
            //     if(i.equals(RPCResult.class)){
            //         isRPCResult = true;
            //         break;
            //     }
            // }

            if(result.getClass().equals(JSONObject.class)){
                JSONObject jsonResult = (JSONObject)result;
                final T obj = jsonResult.toJavaObject(clazz);
                // if(isRPCResult){
                //     RPCResult rpcObj = (RPCResult)obj;
                //     // rpcObj.initInnerClass(jsonResult);
                // }
                return obj;
            }
        }
        
        return null;
    }
}