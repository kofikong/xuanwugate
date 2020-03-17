package com.xuanwugate.blockchain.bitcoin.service;

import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.WebhookService;

/**
 * BitcoinWebhookService
 */
public class BitcoinWebhookService extends WebhookService {

	public BitcoinWebhookService(EndpointConfig config) {
		super(config);
	}
}