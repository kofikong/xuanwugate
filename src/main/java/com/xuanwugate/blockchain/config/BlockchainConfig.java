package com.xuanwugate.blockchain.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.xuanwugate.blockchain.constants.BlockchainConstants;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Constants
 */
@Dependent
public class BlockchainConfig implements BlockchainConstants {
	private static BlockchainConfig instance;
	public static void setInstance(BlockchainConfig instance) {
		BlockchainConfig.instance = instance;
	}

	public static BlockchainConfig getInstance(){
		return BlockchainConfig.instance;
	}

    @ConfigProperty(name="com.xuanwugate.blockchain.rpc.channel", defaultValue ="shell" )
	String blockchainRPCChannel;

    @ConfigProperty(name="com.xuanwugate.blockchain.rpc.channel.timeout.secs", defaultValue ="10" )
	int blockchainRPCChannelTimeout;

    @ConfigProperty(name="com.xuanwugate.blockchain.bitcoin.rpc.network", defaultValue ="mainnet" )
	String bitcoinRPCNetwork;

    @ConfigProperty(name="com.xuanwugate.blockchain.rpc.url.bitcoin.mainnet", defaultValue ="" )
    String bitcoinRPCUriMainnet;

    @ConfigProperty(name="com.xuanwugate.blockchain.rpc.url.bitcoin.testnet", defaultValue ="" )
	String bitcoinRPCUriTestnet;

	@ConfigProperty(name="com.xuanwugate.blockchain.rpc.request.bitcoin.contenttype",defaultValue = "text/plain;charset=UTF-8")
	String bitcoinRPCRequestContentType;

	@ConfigProperty(name="com.xuanwugate.blockchain.rpc.request.bitcoin.authorization.basic",defaultValue = "")
	String bitcoinRPCRequestAuthorizationBasic;
	
	public String getBitcoinRPCUri() {
		if(MAINNET.equals(bitcoinRPCNetwork)){
			return bitcoinRPCUriMainnet;
		}
		else if(TESTNET.equals(bitcoinRPCNetwork)){
			return bitcoinRPCUriTestnet;
		}
		else{
			throw new IllegalArgumentException("com.xuanwugate.blockchain.rpc.url.bitcoin");
		}
	}

	public String getBitcoinRPCRequestContentType() {
		return bitcoinRPCRequestContentType;
	}

	public void setBitcoinRPCRequestContentType(String bitcoinRPCRequestContentType) {
		this.bitcoinRPCRequestContentType = bitcoinRPCRequestContentType;
	}

	public String getBitcoinRPCRequestAuthorizationBasic() {
		return bitcoinRPCRequestAuthorizationBasic;
	}

	public void setBitcoinRPCRequestAuthorizationBasic(String bitcoinRPCRequestAuthorizationBasic) {
		this.bitcoinRPCRequestAuthorizationBasic = bitcoinRPCRequestAuthorizationBasic;
	}

	public String getBitcoinRPCNetwork() {
		return bitcoinRPCNetwork;
	}

	public void setBitcoinRPCNetwork(String bitcoinRPCNetwork) {
		if(MAINNET.equals(bitcoinRPCNetwork) || TESTNET.equals(bitcoinRPCNetwork)){
			this.bitcoinRPCNetwork = bitcoinRPCNetwork;
		}
		else{
			throw new IllegalArgumentException("network");
		}
	}

	public String getBlockchainRPCChannel() {
		return blockchainRPCChannel;
	}

	public void setBlockchainRPCChannel(String blockchainRPCChannel) {
		this.blockchainRPCChannel = blockchainRPCChannel;
	}

	public int getBlockchainRPCChannelTimeout() {
		return blockchainRPCChannelTimeout;
	}

	public void setBlockchainRPCChannelTimeout(int blockchainRPCChannelTimeout) {
		this.blockchainRPCChannelTimeout = blockchainRPCChannelTimeout;
	}	
}