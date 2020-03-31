package com.xuanwugate.blockchain.bitcoin;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.xuanwugate.blockchain.bitcoin.response.BlockResponse;
import com.xuanwugate.blockchain.bitcoin.response.GeneralInformationResponse;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinBlockService;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinBlockchainService;
import com.xuanwugate.client.XuanwuGate;
import com.xuanwugate.response.Response;

@Path("/{version}/bc/btc/{network}")
public class GeneralInformationResource {
    private static final Logger log = Logger.getLogger(GeneralInformationResource.class);

    @Inject
    JsonWebToken jwt;

    @javax.ws.rs.PathParam("version")
    private String version;

    @javax.ws.rs.PathParam("network")
    private String network;

    @GET
    @Path("info")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNodeInformation(@Context SecurityContext ctx) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinBlockchainService service = btc.getBlockchainService();
        GeneralInformationResponse obj = service.getNodeInformation();
        return Response.build(obj);
    }

    @GET
    @Path("blocks/{blockHash_or_blockHeight}")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBlockByHash(@Context SecurityContext ctx, @PathParam("blockHash_or_blockHeight") String blockHash_or_blockHeight) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinBlockService service = btc.getBlockService();
        BlockResponse obj = null;
        if(blockHash_or_blockHeight.matches("[0-9]+")){
          obj = service.getBlockByHeight(blockHash_or_blockHeight);
        }
        else{
          obj = service.getBlockByHash(blockHash_or_blockHeight);
        }
        return Response.build(obj);
    }

    @GET
    @Path("blocks/latest")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBlockLatest(@Context SecurityContext ctx) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinBlockService service = btc.getBlockService();
        BlockResponse obj = service.getBlockLatest();
        return Response.build(obj);
    }
}