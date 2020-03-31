package com.xuanwugate.blockchain.bitcoin.response;

import com.xuanwugate.response.BaseResponse;

/**
 * GetRawTransactionHexResponse
 */
public class GetRawTransactionHexResponse extends BaseResponse{
	private String hex;

	public String getHex() {
		return hex;
	}

	public void setHex(String hex) {
		this.hex = hex;
	}
	
}

/**
	{
		"payload": {
			"hex": "020000000001013be1fe34782f24a2acbacc6adce44e67c70faefba59f122b3c6324d534bd45330100000000fdffffff02f4010000000000002200209f2f1060a8d7ddf0019b4fae87d022ff257e74b9db40d592a29ca0b1d0e7d07aa8430500000000001600149cbc415d3060254333441c1535cad0a34490c81f02473044022069746a36e369cdda9f4dcd9791c061a1e37236334316ebd040729844a9645d55022071fb6aa70467bf7856175033d82bcc63721c122cfcdb8cdfcc41d333035940be012102a4643b893615487f60f20a8197a3f448691c73415357c4173e35ed3acc8cb6f80e6e1900"
		}
	}
*/