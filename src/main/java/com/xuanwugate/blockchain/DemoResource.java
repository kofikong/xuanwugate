package com.xuanwugate.blockchain;

import java.security.Principal;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/demo")
@RequestScoped
public class DemoResource {

    @Context
    ResourceContext resourceContext;
    
    @Inject
    JsonWebToken jwt;
    
    @GET
    @Path("hello")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String helloString(@Context SecurityContext ctx) {
        Principal caller =  ctx.getUserPrincipal();
        String name = caller == null ? "anonymous" : caller.getName();
        boolean hasJWT = jwt.getClaimNames() != null;
        String helloReply = String.format("hello + %s, isSecure: %s, authScheme: %s, hasJWT: %s", name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJWT);
        return helloReply;
    }
}


/*

import io.cryptoapis.blockchains.bitcoin_based.models.Transaction.CreateTransaction;
import io.cryptoapis.blockchains.bitcoin_based.services.TransactionService;
import io.cryptoapis.blockchains.bitcoin_based.services.WalletService;
import io.cryptoapis.client.CryptoApis;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.connections.Bitcoin;
import io.cryptoapis.utils.constants.CryptoApisConstants;
import net.sf.json.JSONObject;

*/