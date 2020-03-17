package com.xuanwugate.blockchain.bitcoin.service;

import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.core.PaymentService;

/**
 * BitcoinPaymentService
 */
public class BitcoinPaymentService extends PaymentService {

	public BitcoinPaymentService(EndpointConfig config) {
		super(config);
	}
}