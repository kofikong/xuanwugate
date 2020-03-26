package com.xuanwugate.rpc;
import com.xuanwugate.blockchain.bitcoin.rpcrequest.BitcoinRequest;
import com.xuanwugate.blockchain.config.BlockchainConfig;
import com.xuanwugate.constants.XuanwuGateConstants;
import com.xuanwugate.rpc.http.HttpProxy;
import com.xuanwugate.rpc.shell.ShellProxy;

/**
 * RPCProxy
 */
public class RPCProxy {

	public static RPCProxyResponse run(BitcoinRequest request){
		switch(BlockchainConfig.getInstance().getBlockchainRPCChannel()){
			case XuanwuGateConstants.SHELL:
				return new ShellProxy().run(request);
			case XuanwuGateConstants.HTTP:
				return new HttpProxy().run(request);
		}

		return null;
	}
}