package com.xuanwugate.blockchain.bitcoin;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.xuanwugate.blockchain.bitcoin.constants.BitcoinCoreConstants;
import com.xuanwugate.blockchain.bitcoin.requestparams.CreateHDWallet;
import com.xuanwugate.blockchain.bitcoin.requestparams.CreateWallet;
import com.xuanwugate.blockchain.bitcoin.response.CreateHDWalletResponse;
import com.xuanwugate.blockchain.bitcoin.response.CreateWalletResponse;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinHDWalletService;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinWalletService;
import com.xuanwugate.blockchain.constants.BlockchainConstants;
import com.xuanwugate.client.XuanwuGate;
import com.xuanwugate.rpc.ErrorInfo;
import com.xuanwugate.rpc.Response;

@Path("/{version}/bc/btc")
public class WalletResource {
    private static final Logger log = Logger.getLogger(WalletResource.class);

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("{network}/wallets")
    // @PermitAll
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createWallet(@Context SecurityContext ctx, @PathParam String version, @PathParam String network,@BeanParam CreateWallet info) {
      try {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinWalletService service = btc.getWalletService();
        CreateWalletResponse obj = service.createWallet(info);
        return Response.build(obj);
      } catch (IOException e) {
        e.printStackTrace();
        log.debug(e.getMessage());
        return Response.error(ErrorInfo.BlockchainConnectionError(BlockchainConstants.BITCOIN));
      }
    }

    @POST
    @Path("{network}/wallets/hd/")
    // @PermitAll
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHDWallet(@Context SecurityContext ctx, @PathParam String version, @PathParam String network,@BeanParam CreateHDWallet info) {
      try {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinHDWalletService service = btc.getHDWalletService();
        CreateHDWalletResponse obj = service.createHDWallet(info);
        return Response.build(obj);
      } catch (IOException e) {
        e.printStackTrace();
        log.debug(e.getMessage());
        return Response.error(ErrorInfo.BlockchainConnectionError(BlockchainConstants.BITCOIN));
      }
    }
}