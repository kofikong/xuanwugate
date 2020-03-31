package com.xuanwugate.blockchain.bitcoin;

import java.io.IOException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import com.xuanwugate.blockchain.bitcoin.response.AddressDetailsResponse;
import com.xuanwugate.blockchain.bitcoin.response.GenerateAddressResponse;
import com.xuanwugate.blockchain.bitcoin.service.BitcoinAddressService;
import com.xuanwugate.client.XuanwuGate;
import com.xuanwugate.response.Response;

@Path("/{version}/bc/btc/{network}")
public class AddressResource {
    private static final Logger log = Logger.getLogger(AddressResource.class);

    @Inject
    JsonWebToken jwt;

    @PathParam("version")
    String version;

    @PathParam("network")
    String network;

    @POST
    @Path("address")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewAddress(@Context SecurityContext ctx) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinAddressService service = btc.getAddressService();
        GenerateAddressResponse obj = service.generateAddress();
        return Response.build(obj);
    }

    @GET
    @Path("address/{address}")
    @RolesAllowed("Subscriber")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddressDetails(@Context SecurityContext ctx, @PathParam("address") String address) {
        XuanwuGate gate = new XuanwuGate(version);
        Bitcoin btc = gate.connectToBtc(network);
        BitcoinAddressService service = btc.getAddressService();
        AddressDetailsResponse obj = service.getAddressDetails(address);
        return Response.build(obj);
    }
}