package com.xuanwugate.blockchain.bitcoin;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.authentication.jwt.TokenUtils;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class WalletResourceTest {
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
    public void testCreateWalletEndpoint() {
        AddressResourceTest addressTest = new AddressResourceTest();
        Map<String,String> jsonAddress1 = addressTest.generateAddressEndpoint(token);
        Map<String,String> jsonAddress2 = addressTest.generateAddressEndpoint(token);
        Assertions.assertTrue(jsonAddress1 != null && jsonAddress2 != null);

        String walletName = "test-"+System.currentTimeMillis();
        // String walletName = "test-1584724066323";
        String[] addresses = new String[2];
        addresses[0] = jsonAddress1.get("address");
        addresses[1] = jsonAddress2.get("address");
        final Response res = given()
        .auth().oauth2(token)
        .queryParam("walletName",walletName)
        .queryParam("addresses",addresses)
        .post("http://localhost:8080/v1/bc/btc/testnet/wallets").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final String payload = bodyJson.getString("payload");
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
    }
}