package com.xuanwugate.response;

/**
 * IResponse
 */
public interface IResponse {
    public void setError(ErrorInfo error);

    public ErrorInfo getError();

    public boolean isError();
}