package com.xuanwugate.response;

import java.util.List;

/**
 * ISimpleResponse
 */
public interface ISimpleResponse<T> {
    public List<T> getPayload();
	public void setPayload(List<T> payload);
}