package com.xuanwugate.configuration;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import io.smallrye.jwt.config.JWTAuthContextInfoProvider;

/**
 * APISettingsProvider
 */
/**
 * A CDI provider for the JWTAuthContextInfo that obtains the necessary information from
 * MP config properties.
 */
@Dependent
public class APISettingsProvider {
    private static final Logger log = Logger.getLogger(JWTAuthContextInfoProvider.class);
    
    public static APISettingsProvider create(){
        final APISettingsProvider obj = new APISettingsProvider();
        log.debug(String.format("Your API Key:%s, Token Duration Secs:", obj.apiKey, obj.tokenDurationSecs));
        return obj;
    }
    /**
     * @since 1.1
     */
    @Inject
    @ConfigProperty(name = "com.xuanwugate.api.key", defaultValue = "your-api-key")
    private String apiKey;

    /**
     * @since 1.1
     */
    @Inject
    @ConfigProperty(name = "com.xuanwugate.api.token.duration.secs", defaultValue = "500")
    private int tokenDurationSecs;

    public int getTokenDurationSecs(){
        return tokenDurationSecs;
    }
}