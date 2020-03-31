package com.xuanwugate.blockchain.bitcoin;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import com.xuanwugate.blockchain.bitcoin.requestparams.Addresses;
import com.xuanwugate.blockchain.bitcoin.requestparams.CreateHDWallet;
import com.xuanwugate.blockchain.bitcoin.requestparams.CreateWallet;
import com.xuanwugate.blockchain.bitcoin.response.CreateHDWalletResponse;
import com.xuanwugate.blockchain.bitcoin.response.CreateWalletResponse;
import com.xuanwugate.blockchain.bitcoin.response.GenerateAddressInWalletResponse;
import com.xuanwugate.blockchain.bitcoin.response.GetWalletDetailsMetaResponse;
import com.xuanwugate.blockchain.bitcoin.response.GetWalletDetailsResponse;
import com.xuanwugate.blockchain.bitcoin.response.ListNormalWalletMetaResponse;
import com.xuanwugate.blockchain.bitcoin.response.ListNormalWalletResponse;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinHDWalletService;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinWalletService;
import com.xuanwugate.client.XuanwuGate;
import com.xuanwugate.response.Response;

@Path("/{version}/bc/btc/{network}")
public class WalletResource {
    private static final Logger log = Logger.getLogger(WalletResource.class);

    @Inject
    JsonWebToken jwt;

    @PathParam("version")
    String version;

    @PathParam("network")
    String network;

    /**
     * Create normal wallet
     * @param ctx 
     * @param info  Request Wallet Object { "walletName" : My_WALLET_NAME}, "addresses" : [ADDRESS1,ADDRESS2,...] }
     * @return 
     * example: {"payload": {"addresses": [ADDRESS1,ADDRESS2,...],"walletName": "demowallet"}}
     */
    @POST
    @Path("wallets")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createWallet(@Context SecurityContext ctx,@BeanParam CreateWallet info) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinWalletService service = btc.getWalletService();
        CreateWalletResponse obj = service.createWallet(info);
        return Response.build(obj);
    }

    /**
     * List normal wallet
     * @param ctx
     * @return 
     * example: {"payload": ["walletName1", "walletName2"], "meta":{"totalCount":2, "results":2}}
     */
    @GET
    @Path("wallets")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listNormalWallet(@Context SecurityContext ctx) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinWalletService service = btc.getWalletService();
        ListNormalWalletResponse obj = service.listNormalWallet();
        ListNormalWalletMetaResponse meta = new ListNormalWalletMetaResponse();
        if(obj != null){
            meta.setTotalCount(obj.getPayload().size());
            meta.setResults(obj.getPayload().size());
        }
        
        return Response.build(obj).setMeta(meta);
    }


    
    /**
     * Get normal wallet details
     * @param ctx 
     * @param walletName
     * @return 
     * example: {"payload": {"walletName": "demowallet", "addresses": [{"address":"ADDRESS1","balance":"0.0001"}],"totalBalance": "0.0001"}, "meta":{"totalCount": 1, "limit": 50, "results": 1}}
     */
    @GET
    @Path("wallets/{walletName}")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNormalWalletDetails(@Context SecurityContext ctx,@PathParam("walletName") String walletName) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinWalletService service = btc.getWalletService();
        GetWalletDetailsResponse obj = service.getWalletDetails(walletName);
        GetWalletDetailsMetaResponse meta = new GetWalletDetailsMetaResponse();
        if(obj != null){
            meta.setTotalCount(obj.getAddresses().size());
            meta.setLimit(obj.getAddresses().size());
            meta.setResults(obj.getAddresses().size());
        }
        
        return Response.build(obj).setMeta(meta);
    }

    /**
     * Add address to normal Wallet
     * @param ctx 
     * @param walletName
     * @return 
     * example: {"payload": {"addresses": [ADDRESS1,ADDRESS2,...],"walletName": "demowallet"}}
     */
    @POST
    @Path("wallets/{walletName}/addresses")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAddressesToNormalWallet(@Context SecurityContext ctx,@PathParam("walletName") String walletName, @BeanParam Addresses addresses) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinWalletService service = btc.getWalletService();
        CreateWalletResponse obj = service.addAddressesToNormalWallet(walletName,addresses.getAddresses());
        return Response.build(obj);
    }

    /**
     * Generate address in normal wallet
     * @param ctx 
     * @param walletName
     * @return 
     * example: {"payload": {"addresses": [ADDRESS1,ADDRESS2,...],"walletName": "demowallet"}}
     */
    @POST
    @Path("wallets/{walletName}/addresses/generate")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateAddressInWallet(@Context SecurityContext ctx,@PathParam("walletName") String walletName) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinWalletService service = btc.getWalletService();
        GenerateAddressInWalletResponse obj = service.generateAddressInNormalWallet(walletName);
        return Response.build(obj);
    }

    @POST
    @Path("wallets/hd/")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHDWallet(@Context SecurityContext ctx, @BeanParam CreateHDWallet info) {
        //TODO:: Need to save wallet name to database
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinHDWalletService service = btc.getHDWalletService();
        CreateHDWalletResponse obj = service.createHDWallet(info);
        return Response.build(obj);
    }
}