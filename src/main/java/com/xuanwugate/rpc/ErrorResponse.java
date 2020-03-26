package com.xuanwugate.rpc;

/**
 * Errorload
 */
public class ErrorResponse  extends Response{
    private Meta meta;

    public ErrorResponse(ErrorInfo error){
        this.meta = new Meta(error);
    }

    public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
    }
    
    public class Meta{
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