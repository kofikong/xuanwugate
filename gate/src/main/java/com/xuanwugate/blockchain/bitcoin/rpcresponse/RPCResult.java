package com.xuanwugate.blockchain.bitcoin.rpcresponse;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.rpc.ErrorInfo;

public abstract class RPCResult {
    private ErrorInfo error;
    
    public static <T> T parse(final Class<T> clazz,final String jsonStr){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        return parse(clazz,jsonObject);
    }
    public static <T> T parse(final Class<T> clazz,final JSONObject jsonObject){
        if(jsonObject == null){
            return null;
        }

        final JSONObject jsonError = jsonObject.getJSONObject("error");
        final Object result = jsonObject.get("result");

        if(String.class.equals(clazz)){
            return (T)result;
        }
        else {
            Class<?>[] interfaces = clazz.getInterfaces();
            boolean isRPCSpecificResult = false;
            
            for(Class<?> i : interfaces){
                if(i.equals(RPCSpecificResult.class)){
                    isRPCSpecificResult = true;
                    break;
                }
            }
            
            if(result.getClass().equals(JSONObject.class)){
                JSONObject jsonResult = (JSONObject)result;
                T obj = null;
                if(isRPCSpecificResult){
                    RPCSpecificResult objTmp;
					try {
                        objTmp = (RPCSpecificResult)clazz.newInstance();
                        objTmp.init(jsonResult);
                        obj = (T)objTmp;
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
                }
                else{
                    obj = jsonResult.toJavaObject(clazz);
                    if(RPCResult.class.isAssignableFrom(clazz)){
                        RPCResult rpcObj = (RPCResult)obj;
                        if(jsonError != null){
                            ErrorInfo error = jsonError.toJavaObject(ErrorInfo.class);
                            rpcObj.setError(error);
                        }
                    }
                }
                
                return obj;
            }
            else if(result.getClass().equals(JSONArray.class)){
                JSONArray jsonResult = (JSONArray)result;
            
                if(isRPCSpecificResult){
                    RPCSpecificResult objTmp;
					try {
                        objTmp = (RPCSpecificResult)clazz.newInstance();
                        objTmp.init(jsonResult);
                    return (T)objTmp;
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
                }
                else if("java.util.ArrayList".equals(clazz.getName())){
                    ArrayList<String> obj = new ArrayList<String>();
                    for(int index =0; index < jsonResult.size(); index++){
                        obj.add(jsonResult.getString(index));
                    }
                    return (T) obj;
                }
            }
        }
        
        return null;
    }


    public ErrorInfo getError(){
        return this.error;
    }

    public void setError(ErrorInfo error){
        this.error = error;
    }
}