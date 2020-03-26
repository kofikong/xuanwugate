package com.xuanwugate.blockchain.bitcoin;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;

import com.xuanwugate.authentication.jwt.TokenUtils;

@QuarkusTest
public class GeneralInformationResourceTest {
    /**
     * The test generated JWT token string
     */
    private String token;

    @BeforeEach
    public void generateToken() throws Exception {
        HashMap<String, Long> timeClaims = new HashMap<>();
        token = TokenUtils.generateTokenString("/JwtClaims.json", timeClaims);
    }

    @Test
    public void testGetNodeInformationEndpoint() {
        final Response res = given()
        .auth().oauth2(token)
        .get("http://localhost:8080/v1/bc/btc/testnet/info").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final String payload = bodyJson.getString("payload");
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
    }

    @Test
    public void testGetBlockByHashEndpoint() {
        final Response res = given()
        .auth().oauth2(token)
        .get("http://localhost:8080/v1/bc/btc/mainnet/blocks/00000000c937983704a73af28acdec37b049d214adbda81d7e2a3dd146f6ed09").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final String payload = bodyJson.getString("payload");
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
    }

    @Test
    public void testGetBlockByHeightEndpoint() {
        final Response res = given()
        .auth().oauth2(token)
        .get("http://localhost:8080/v1/bc/btc/testnet/blocks/564349").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final String payload = bodyJson.getString("payload");
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
    }

    @Test
    public void testGetBlockLasterEndpoint() {
        final Response res = given()
        .auth().oauth2(token)
        .get("http://localhost:8080/v1/bc/btc/testnet/blocks/latest").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final String payload = bodyJson.getString("payload");
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
    }
}