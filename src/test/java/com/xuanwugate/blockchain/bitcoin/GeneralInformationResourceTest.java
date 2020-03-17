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
public class GeneralInformationResourceTest {
    @Test
    public void testGetNodeInformationEndpoint() {
        GenerateTokenResourceTest token = new GenerateTokenResourceTest();
        final String token_id = token.getJWTGenerateToken();
        final Response res = given()
        .header("X-API-KEY", "your-api-key")
        .header("Authorization", "Bearer "+token_id)
        .get("http://localhost:8080/v1/bc/btc/mainnet/info").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final int errorCode = bodyJson.getInt("errorCode");
        final String payload = bodyJson.getString("payload");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(errorCode == 0 && payload != null && !token_id.isEmpty());
    }

    @Test
    public void testGetBlockByHashEndpoint() {
        GenerateTokenResourceTest token = new GenerateTokenResourceTest();
        final String token_id = token.getJWTGenerateToken();
        final Response res = given()
        .header("X-API-KEY", "your-api-key")
        .header("Authorization", "Bearer "+token_id)
        .get("http://localhost:8080/v1/bc/btc/mainnet/blocks/00000000c937983704a73af28acdec37b049d214adbda81d7e2a3dd146f6ed09").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final int errorCode = bodyJson.getInt("errorCode");
        final String payload = bodyJson.getString("payload");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(errorCode == 0 && payload != null && !token_id.isEmpty());
    }

    @Test
    public void testGetBlockByHeightEndpoint() {
        GenerateTokenResourceTest token = new GenerateTokenResourceTest();
        final String token_id = token.getJWTGenerateToken();
        final Response res = given()
        .header("X-API-KEY", "your-api-key")
        .header("Authorization", "Bearer "+token_id)
        .get("http://localhost:8080/v1/bc/btc/mainnet/blocks/564349").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final int errorCode = bodyJson.getInt("errorCode");
        final String payload = bodyJson.getString("payload");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(errorCode == 0 && payload != null && !token_id.isEmpty());
    }

    @Test
    public void testGetBlockLasterEndpoint() {
        GenerateTokenResourceTest token = new GenerateTokenResourceTest();
        final String token_id = token.getJWTGenerateToken();
        final Response res = given()
        .header("X-API-KEY", "your-api-key")
        .header("Authorization", "Bearer "+token_id)
        .get("http://localhost:8080/v1/bc/btc/mainnet/blocks/latest").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final int errorCode = bodyJson.getInt("errorCode");
        final String payload = bodyJson.getString("payload");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(errorCode == 0 && payload != null && !token_id.isEmpty());
    }
}