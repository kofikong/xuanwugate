package com.xuanwugate.rpc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.response.BaseResponse;
import com.xuanwugate.response.ErrorInfo;

/**
 * RPCResultFactory
 */
@SuppressWarnings("unchecked")
public final class RPCResultFactory {
    public static <T> T parse(final Class<T> clazz,final String jsonStr){
        final JSONObject jsonObject = JSONObject.parseObject(jsonStr);
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
            final Class<?>[] interfaces = clazz.getInterfaces();
            boolean isRPCSpecificResult = false;
            
            for(final Class<?> i : interfaces){
                if(i.equals(RPCSpecificResult.class)){
                    isRPCSpecificResult = true;
                    break;
                }
            }
            
            if(jsonError != null){
                try {
                    final T obj = (T)clazz.getDeclaredConstructor().newInstance();
                    if(BaseResponse.class.isAssignableFrom(clazz)){
                        final BaseResponse rpcObj = (BaseResponse)obj;
                        final ErrorInfo errInfo = jsonError.toJavaObject(ErrorInfo.class);
                        rpcObj.setError(errInfo);
                    }                    
                    return obj;
				} catch (final Exception e) {
                    e.printStackTrace();
                    return null;
				}
            }
            else if(result.getClass().equals(JSONObject.class)){
                final JSONObject jsonResult = (JSONObject)result;
                T obj = null;
                if(isRPCSpecificResult){
                    RPCSpecificResult objTmp;
					try {
                        objTmp = (RPCSpecificResult)clazz.getDeclaredConstructor().newInstance();
                        objTmp.init(jsonResult);
                        obj = (T)objTmp;
					} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
						e.printStackTrace();
					}
                }
                else{
                    obj = jsonResult.toJavaObject(clazz);
                }
                
                return obj;
            }
            else if(result.getClass().equals(JSONArray.class)){
                final JSONArray jsonResult = (JSONArray)result;
            
                if(isRPCSpecificResult){
                    RPCSpecificResult objTmp;
					try {
                        objTmp = (RPCSpecificResult)clazz.getDeclaredConstructor().newInstance();
                        objTmp.init(jsonResult);
                    return (T)objTmp;
					} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
						e.printStackTrace();
					}
                }
                else if("java.util.ArrayList".equals(clazz.getName())){
                    final ArrayList<String> obj = new ArrayList<String>();
                    for(int index =0; index < jsonResult.size(); index++){
                        obj.add(jsonResult.getString(index));
                    }
                    return (T) obj;
                }
            }
        }
        
        return null;
    }
    
}