package com.xuanwugate.client;

import com.xuanwugate.blockchain.bitcoin.Bitcoin;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.config.BlockchainConfig;
import com.xuanwugate.blockchain.constants.BlockchainConstants;

/**
 * XuanwuGate
 */
public class XuanwuGate {
    public XuanwuGate(String version){
        if(version != null){
            if(BlockchainConstants.VERSION_V1.equals(version)){
                this.version = BlockchainConstants.VERSION_V1;
            }
            else{
                throw new IllegalArgumentException("version");
            }
        }
    }
    private String version = BlockchainConstants.VERSION_V1;

    public Bitcoin connectToBtc(String network) {
        BlockchainConfig.getInstance().setBitcoinRPCNetwork(network);
        EndpointConfig conf = setBlockChainConfig(BlockchainConstants.BITCOIN,network);
        // String url = this.getBitcoinRPCUri();
        // return url.isEmpty();
        return new Bitcoin(conf);
    }

    private EndpointConfig setBlockChainConfig(String blockchain, String network) {
        EndpointConfig newConfig = setConfig();
        newConfig.setBlockchain(blockchain);
        newConfig.setNetwork(network);
        // this.setBitcoinRPCNetwork(network);
        return newConfig;
    }

    private EndpointConfig setConfig() {
        return new EndpointConfig(version);
    }
}