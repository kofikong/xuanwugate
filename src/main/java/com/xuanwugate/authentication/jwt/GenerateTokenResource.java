package com.xuanwugate.authentication.jwt;
import java.util.HashMap;
import java.util.Map;


import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logging.Logger;

import com.xuanwugate.configuration.APISettingsProvider;

@Path("/v1/authentication/jwt")
@RequestScoped
public class GenerateTokenResource {
    private static final String TOKEN_L_CREATE_TIME_S = "Token: %s ,create time: %d";

    private static final Logger log = Logger.getLogger(GenerateTokenResource.class);

    @Inject
    APISettingsProvider apiSettings;

    @GET()
    @Path("generatetoken")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> generateToken(@Context final SecurityContext ctx) throws Exception {
        final Map<String,String> res = new HashMap<>();// Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        final HashMap<String,Long> timeClaims = new HashMap<>();
        final long duration = apiSettings.getTokenDurationSecs();
        final long currentTimeS = TokenUtils.currentTimeInSecs();
        final long exp = currentTimeS + duration;
        timeClaims.put(Claims.exp.name(), exp);
        final String token = TokenUtils.generateTokenString(timeClaims);
        log.infof(TOKEN_L_CREATE_TIME_S, token, currentTimeS);
        res.put("Token", token);
		return res;
    }
}