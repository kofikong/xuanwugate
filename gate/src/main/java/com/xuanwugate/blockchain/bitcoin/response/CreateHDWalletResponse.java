package com.xuanwugate.blockchain.bitcoin.response;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.rpc.ErrorInfo;
import com.xuanwugate.rpc.IPayload;

/**
 * WalletResponse
 */
public class CreateHDWalletResponse implements IPayload  {
    private List<HDAddress> addresses;
    private String walletName;
    
    public static CreateHDWalletResponse parse(JSONObject jsonResult){
        CreateHDWalletResponse obj = jsonResult.toJavaObject(CreateHDWalletResponse.class);
		return obj;
	}

	public List<HDAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<HDAddress> addresses) {
		this.addresses = addresses;
	}

	public String getWalletName() {
		return walletName;
	}

	public void setWalletName(String walletName) {
		this.walletName = walletName;
	}

	@Override
	public void setError(ErrorInfo error) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ErrorInfo getError() {
		// TODO Auto-generated method stub
		return null;
	}

	public class HDAddress{
		private String path;
		private String address;
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}

	}
}