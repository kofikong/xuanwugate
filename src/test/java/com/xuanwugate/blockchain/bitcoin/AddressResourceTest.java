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
public class AddressResourceTest {

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
    public void testGenerateAddressEndpoint() {
        final Response res = given().auth()
        .oauth2(token)
        .post("/v1/bc/btc/testnet/address").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final int errorCode = bodyJson.getInt("errorCode");
        final String payload = bodyJson.getString("payload");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(errorCode == 0 && payload != null);
    }
}