package com.xuanwugate.blockchain.bitcoin;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import com.xuanwugate.authentication.jwt.GenerateTokenResourceTest;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class WalletResourceTest {
    @Test
    public void testCreateWalletEndpoint() {
        String[] addresses = new String[2];
        addresses[0] = "1aaaaa";
        addresses[1] = "1bbbbb";
        final Response res = given()
        .header("X-API-KEY", "your-api-key")
        .queryParam("walletName","DemoWallet1")
        .queryParam("addresses",addresses)
        .post("/v1/bc/btc/mainnet/wallets").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final int errorCode = bodyJson.getInt("errorCode");
        final String payload = bodyJson.getString("payload");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(errorCode == 0 && payload != null);
    }
}