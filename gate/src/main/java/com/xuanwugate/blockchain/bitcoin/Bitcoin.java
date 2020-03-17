package com.xuanwugate.blockchain.bitcoin;

import java.lang.reflect.InvocationTargetException;

import com.xuanwugate.blockchain.bitcoin.service.BitcoinAddressService;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinBlockService;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinBlockchainService;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinPaymentService;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinTransactionService;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinWalletService;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinWebhookService;
import com.xuanwugate.blockchain.common.EndpointConfig;
import com.xuanwugate.blockchain.config.BlockchainConfig;
import com.xuanwugate.blockchain.core.BaseEndpoint;

/**
 * Bitcoin
 */
public class Bitcoin extends BaseEndpoint<BitcoinBlockchainService,BitcoinBlockService,BitcoinAddressService,BitcoinTransactionService,BitcoinWalletService,BitcoinWebhookService,BitcoinPaymentService>{

    public Bitcoin(EndpointConfig endpointConfig) {
        super(endpointConfig);
    }

	@Override
	protected BitcoinBlockchainService initBlockchainService(EndpointConfig config){
		try {
            if(BlockchainConfig.VERSION_V1.equals(config.getVersion())){
                return getConstructor(BitcoinBlockchainService.class).newInstance(config);
            }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
	}

	@Override
	protected BitcoinBlockService initBlockService(EndpointConfig config) {
		try {
            if(BlockchainConfig.VERSION_V1.equals(config.getVersion())){
                return getConstructor(BitcoinBlockService.class).newInstance(config);
            }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
	}

	@Override
	protected BitcoinAddressService initAddressService(EndpointConfig config) {
		try {
            if(BlockchainConfig.VERSION_V1.equals(config.getVersion())){
                return getConstructor(BitcoinAddressService.class).newInstance(config);
            }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
     
	@Override
	protected BitcoinTransactionService initTransactionService(EndpointConfig config) {
		try {
            if(BlockchainConfig.VERSION_V1.equals(config.getVersion())){
                return getConstructor(BitcoinTransactionService.class).newInstance(config);
            }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
	}

	@Override
	protected BitcoinWalletService initWalletService(EndpointConfig config) {
		try {
            if(BlockchainConfig.VERSION_V1.equals(config.getVersion())){
                return getConstructor(BitcoinWalletService.class).newInstance(config);
            }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
	}

	@Override
	protected BitcoinPaymentService initPaymentService(EndpointConfig config) {
		try {
            if(BlockchainConfig.VERSION_V1.equals(config.getVersion())){
                return getConstructor(BitcoinPaymentService.class).newInstance(config);
            }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
	}

	@Override
	protected BitcoinWebhookService initWebhookService(EndpointConfig config) {
		try {
            if(BlockchainConfig.VERSION_V1.equals(config.getVersion())){
                return getConstructor(BitcoinWebhookService.class).newInstance(config);
            }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
	}
    
}