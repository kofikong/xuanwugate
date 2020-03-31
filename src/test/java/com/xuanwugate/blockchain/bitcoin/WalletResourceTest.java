package com.xuanwugate.blockchain.bitcoin;

import com.xuanwugate.authentication.jwt.TokenUtils;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

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
        .post("/v1/bc/btc/testnet/wallets").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        //Response Body, maybe it well null.
        final String payload = bodyJson.getString("payload");
        //Response Error
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
    }

    @Test
    public void testListNormalWalletEndpoint() {
        final Response res = given()
        .auth().oauth2(token)
        // .header("Authorization", "Bearer 1"+token)
        .get("/v1/bc/btc/testnet/wallets").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        //Response Body, maybe it well null.
        final String payload = bodyJson.getString("payload");
        //Response Error
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
    }

    @Test
    public void testGetNormalWalletDetailsEndpoint() {
        final Response res = given()
        .auth().oauth2(token)
        // .header("Authorization", "Bearer 1"+token)
        .get("/v1/bc/btc/testnet/wallets/koftestnet").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        //Response Body, maybe it well null.
        final String payload = bodyJson.getString("payload");
        //Response Error
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
    }

    @Test
    public void testAddAddressesToNormalWalletEndpoint() {
        AddressResourceTest addressTest = new AddressResourceTest();
        Map<String,String> jsonAddress1 = addressTest.generateAddressEndpoint(token);
        Map<String,String> jsonAddress2 = addressTest.generateAddressEndpoint(token);
        Assertions.assertTrue(jsonAddress1 != null && jsonAddress2 != null);

        String walletName = "test-1584724066323";
        String[] addresses = new String[2];
        addresses[0] = jsonAddress1.get("address");
        addresses[1] = jsonAddress2.get("address");
        final Response res = given()
        .auth().oauth2(token)
        .queryParam("addresses",addresses)
        .post("/v1/bc/btc/testnet/wallets/"+walletName+"/addresses").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        //Response Body, maybe it well null.
        final String payload = bodyJson.getString("payload");
        //Response Error
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
        Map<String,String> info = bodyJson.getMap("payload",String.class ,String.class);
        info = null;
    }

    @Test
    public void testGenerateAddressInWalletEndpoint() {
        String walletName = "test-1584724066323";
        final Response res = given()
        .auth().oauth2(token)
        .post("/v1/bc/btc/testnet/wallets/"+walletName+"/addresses/generate").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        //Response Body, maybe it well null.
        final String payload = bodyJson.getString("payload");
        //Response Error
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
        Map<String,String> info = bodyJson.getMap("payload",String.class ,String.class);
        info = null;
    }
}