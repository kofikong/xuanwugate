package com.xuanwugate.rpc;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
            
            if(jsonError != null){
                try {
                    T obj = (T)clazz.getDeclaredConstructor().newInstance();
                    if(RPCResult.class.isAssignableFrom(clazz)){
                        RPCResult rpcObj = (RPCResult)obj;
                        ErrorInfo errInfo = jsonError.toJavaObject(ErrorInfo.class);
                        rpcObj.setError(errInfo);
                    }
                    return obj;
				} catch (Exception e) {
                    e.printStackTrace();
                    return null;
				}
            }
            else if(result.getClass().equals(JSONObject.class)){
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

    public boolean isError(){
        return this.error != null;
    }
}