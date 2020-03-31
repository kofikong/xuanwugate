package com.xuanwugate.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import com.xuanwugate.blockchain.config.BlockchainConfig;
import com.xuanwugate.store.RocksDBUtils;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import io.vertx.core.http.HttpServerRequest;

/**
 * RequestFilter
 */
@Provider
public class RequestFilter implements ContainerRequestFilter {
    private static final Logger log = Logger.getLogger(RequestFilter.class);

    @ConfigProperty(name="com.xuanwugate.api.db.name",defaultValue="xuanwugate.db")
    private String dbName;

    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;

    @Inject
    BlockchainConfig instance;
    
	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		final String method = context.getMethod();
        final String path = info.getPath();
        final String address = request.remoteAddress().toString();
        log.infof("Request %s %s from IP %s", method, path, address);
        BlockchainConfig.setInstance(instance);
        RocksDBUtils.createAndLoadDB(dbName);

        String oauth2 = request.getHeader("Authorization");
        oauth2 = "";
        // final String xApiKey = request.getHeader("X-API-KEY");
        // if(!apiKey.equals(xApiKey)){
        //     ResponseBuilder rb = Response.status(401);
        //     context.abortWith(rb.build());
        //     return;
        // }

        // Principal caller = ctx.getUserPrincipal();
        // if(caller == null){
        //     ResponseBuilder rb = Response.status(401);
        //     context.abortWith(rb.build());
        //     return;
        // }
	}
}