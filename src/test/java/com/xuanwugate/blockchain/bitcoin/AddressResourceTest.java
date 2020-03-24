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
public class AddressResourceTest {
    @Test
    public void testGenerateAddressEndpoint() {
        final Response res = given()
        .header("X-API-KEY", "your-api-key")
        .post("/v1/bc/btc/testnet/address").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final int errorCode = bodyJson.getInt("errorCode");
        final String payload = bodyJson.getString("payload");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(errorCode == 0 && payload != null);
    }
}