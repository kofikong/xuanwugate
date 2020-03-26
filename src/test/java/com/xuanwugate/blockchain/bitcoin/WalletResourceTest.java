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
        String[] addresses = new String[2];
        addresses[0] = "1aaaaa";
        addresses[1] = "1bbbbb";
        final Response res = given().auth()
        .oauth2(token)
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