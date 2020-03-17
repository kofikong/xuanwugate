package com.xuanwugate.authentication.jwt;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

/**
 * Utilities for generating a JWT
 */
public class TokenUtils {

    private TokenUtils() {
        // no-op: utility class
    }

    /**
     * Utility method to generate a JWT string that is signed by the privateKey.pem
     * test resource key, possibly with invalid fields.
     *
     * @param timeClaims - used to return the exp, iat, auth_time claims
     * @return the JWT string
     * @throws Exception on parse failure
     */
    public static String generateTokenString(final Map<String, Long> timeClaims)
            throws Exception {
        // Use the test private key associated with the test public key for a valid signature
        final String privateKeyPath = "/privateKey.pem";
        final PrivateKey pk = readPrivateKey(privateKeyPath);
        return generateTokenString(pk, privateKeyPath, timeClaims);
    }
    
    public static String generateTokenString(final PrivateKey privateKey, final String kid, 
         final Map<String, Long> timeClaims) throws Exception {
    
        JwtClaimsBuilder claims = null;
        try {
            claims = Jwt.claims();
            final Map<String, Object> claimsData = new HashMap<>();

            claimsData.put(Claims.iss.name(), "https://quarkus.io/using-jwt-rbac");
            claimsData.put(Claims.jti.name(), "a-123");
            claimsData.put(Claims.sub.name(), "jdoe-using-jwt-rbac");
            claimsData.put(Claims.upn.name(), "ko.f2005@163.io");
            claimsData.put(Claims.preferred_username.name(), "jdoe");
            claimsData.put(Claims.aud.name(), "using-jwt-rbac");
            claimsData.put(Claims.birthdate.name(), "2020-03-10");

            final LinkedHashMap<String,String> roleMappingsData = new LinkedHashMap<>();
            roleMappingsData.put("group1", "Group1MappedRole");
            roleMappingsData.put("group2", "Group2MappedRole");
            claimsData.put("roleMappings", roleMappingsData);

            final List<String> groupsData = new ArrayList<>();
            groupsData.add("Admin");
            groupsData.add("User");
            groupsData.add("Subscriber");
            claimsData.put("groups", groupsData);
            claims = Jwt.claims(claimsData);

        } catch (final Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    
         final long currentTimeInSecs = currentTimeInSecs();
        final long exp = timeClaims != null && timeClaims.containsKey(Claims.exp.name()) 
        		? timeClaims.get(Claims.exp.name()) : currentTimeInSecs + 300;
        
        claims.issuedAt(currentTimeInSecs);
        claims.claim(Claims.auth_time.name(), currentTimeInSecs);
        claims.expiresAt(exp);
        
        return claims.jws().signatureKeyId(kid).sign(privateKey);
    }
    /**
     * Read a PEM encoded private key from the classpath
     *
     * @param pemResName - key file resource name
     * @return PrivateKey
     * @throws Exception on decode failure
     */
    public static String readResource(final String resName) throws Exception{
        try (InputStream contentIS = TokenUtils.class.getResourceAsStream(resName)) {
            final byte[] tmp = new byte[4096];
            final int length = contentIS.read(tmp);
            return new String(tmp, 0, length, "UTF-8");
        }
    }
    /**
     * Read a PEM encoded private key from the classpath
     *
     * @param pemResName - key file resource name
     * @return PrivateKey
     * @throws Exception on decode failure
     */
    public static PrivateKey readPrivateKey(final String pemResName) throws Exception {
        return decodePrivateKey(readResource(pemResName));
    }

    /**
     * Generate a new RSA keypair.
     *
     * @param keySize - the size of the key
     * @return KeyPair
     * @throws NoSuchAlgorithmException on failure to load RSA key generator
     */
    public static KeyPair generateKeyPair(final int keySize) throws NoSuchAlgorithmException {
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    /**
     * Decode a PEM encoded private key string to an RSA PrivateKey
     *
     * @param pemEncoded - PEM string for private key
     * @return PrivateKey
     * @throws Exception on decode failure
     */
    public static PrivateKey decodePrivateKey(final String pemEncoded) throws Exception {
        final byte[] encodedBytes = toEncodedBytes(pemEncoded);

        final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    /**
     * Decode a PEM encoded public key string to an RSA PublicKey
     *
     * @param pemEncoded - PEM string for private key
     * @return PublicKey
     * @throws Exception on decode failure
     */
    public static PublicKey decodePublicKey(final String pemEncoded) throws Exception {
        final byte[] encodedBytes = toEncodedBytes(pemEncoded);

        final X509EncodedKeySpec spec = new X509EncodedKeySpec(encodedBytes);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    private static byte[] toEncodedBytes(final String pemEncoded) {
        final String normalizedPem = removeBeginEnd(pemEncoded);
        return Base64.getDecoder().decode(normalizedPem);
    }

    private static String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }

    /**
     * @return the current time in seconds since epoch
     */
    public static int currentTimeInSecs() {
        final long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
}
