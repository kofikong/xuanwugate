package com.xuanwugate.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import com.xuanwugate.blockchain.config.BlockchainConfig;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import io.vertx.core.http.HttpServerRequest;

/**
 * RequestFilter
 */
@Provider
public class RequestFilter implements ContainerRequestFilter {
    private static final Logger log = Logger.getLogger(RequestFilter.class);

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
	}
}