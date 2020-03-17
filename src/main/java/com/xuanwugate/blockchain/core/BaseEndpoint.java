package com.xuanwugate.blockchain.core;
import com.xuanwugate.blockchain.common.EndpointConfig;

/**
 * BaseEndpoint
 * @param <BCS> BlockchainService
 * @param <BS> BlockService
 * @param <AS> AddressService
 * @param <TS> TransactionService
 * @param <WS> WalletService
 * @param <WHS> WebhookService
 * @param <PS> PaymentService
 */
public abstract class BaseEndpoint<BCS extends BlockchainService,
BS extends BlockService,
AS extends AddressService,
TS extends TransactionService,
WS extends WalletService,
WHS extends WebhookService,
PS extends PaymentService> extends BaseConstructor {
    protected TS transactionService;
    protected BCS blockchainService;
    protected BS blockService;
    protected AS addressService;
    protected WS walletService;
    protected WHS webhookService;
    protected PS paymentService;

    public BaseEndpoint(EndpointConfig config){
        this.config = config;
        initService(config);
    }

    protected void initService(EndpointConfig config){
        try {
            this.transactionService = initTransactionService(config);
            this.blockchainService = initBlockchainService(config);
            this.blockService = initBlockService(config);
            this.addressService = initAddressService(config);
            this.walletService   = initWalletService(config);
            this.paymentService = initPaymentService(config);
            this.webhookService = initWebhookService(config);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract BCS initBlockchainService(EndpointConfig config) throws Exception;
    protected abstract BS initBlockService(EndpointConfig config);
    protected abstract AS initAddressService(EndpointConfig config);
    protected abstract TS initTransactionService(EndpointConfig config);
    protected abstract WS initWalletService(EndpointConfig config);
    protected abstract PS initPaymentService(EndpointConfig config);
    protected abstract WHS initWebhookService(EndpointConfig config);

	public TS getTransactionService() {
		return this.transactionService;
	}

	public BCS getBlockchainService() {
		return this.blockchainService;
	}

	public BS getBlockService() {
		return this.blockService;
	}

	public AS getAddressService() {
		return this.addressService;
    }
    
	public WS getWalletService() {
		return this.walletService;
	}

	public WHS getWebhookService() {
		return this.webhookService;
    }
    
	public PS getPaymentService() {
		return this.paymentService;
	}
}