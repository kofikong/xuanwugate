package com.xuanwugate.response;
import java.util.HashMap;

/**
 * Build response construction data for XuanwuGate
 */
public class Response extends HashMap<String,Object>{
    /**
	 *
	 */
	private static final long serialVersionUID = -3769526493372454685L;

	public static Response build(IResponse load){
        Response obj = new Response(load);
        return obj;
    }

    public Response (){}
    
    public Response (IResponse load){
        if(load == null || load.getError() != null){
            this.put("meta", load == null ? new Meta(ErrorInfo.GeneralError("no data")): load);
        }
        else{
            if(isInterface(ISimpleResponse.class, load)){
                ISimpleResponse simpleObj = (ISimpleResponse)load;
                this.put("payload", simpleObj.getPayload());
            }
            else{
                this.put("payload", load);
            }
        }
    }

    public Response setMeta(IResponse load){
        ErrorInfo error = null;
        if(this.containsKey("meta")){
            Object objObj = this.get("meta");
            if(isInterface(IResponse.class,objObj)){
                IResponse old = (IResponse)objObj;
                error = old.getError();
            }
            
            if(load != null){
                load.setError(error);
            }

            this.put("meta", load);
        }
        else{
            this.put("meta", load);
        }
        
        return this;
    }

    private boolean isInterface(Class clazz,Object obj){
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        boolean is = false;
    
        for(Class<?> i : interfaces){
            if(i.equals(clazz)){
                is = true;
                break;
            }
        }

        return is;
    }

    public class Meta extends BaseResponse{
        private ErrorInfo error;

        public Meta(ErrorInfo error){
            this.error = error;
        }

		public ErrorInfo getError() {
			return error;
		}

		public void setError(ErrorInfo error) {
			this.error = error;
		}
    }
}