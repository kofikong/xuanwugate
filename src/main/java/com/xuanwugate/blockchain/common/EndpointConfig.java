package com.xuanwugate.blockchain.common;

/**
 * EndpointConfig
 */
public class EndpointConfig {
    private String version;
    private String blockchain;
    private String network;

    public EndpointConfig(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public String getNetwork() {
        return network;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
}