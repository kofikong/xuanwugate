package com.xuanwugate.blockchain.bitcoin;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GeneralInformationResourceTest {
    @Test
    public void testGetNodeInformationEndpoint() {
        given()
          .header("X-API-KEY", "your-api-key")
          .when().get("/v1/bc/btc/mainnet/info")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testGetBlockByHashEndpoint() {
        given()
          .header("X-API-KEY", "your-api-key")
          .when().get("/v1/bc/btc/mainnet/blocks/00000000c937983704a73af28acdec37b049d214adbda81d7e2a3dd146f6ed09")
          .then()
          .statusCode(200)
          .body(is("hello"));
    }

    @Test
    public void testGetBlockByHeightEndpoint() {
        given()
          .header("X-API-KEY", "your-api-key")
          .when().get("/v1/bc/btc/mainnet/blocks/564349")
          .then()
          .statusCode(200)
          .body(is("hello"));
    }

    @Test
    public void testGetBlockLasterEndpoint() {
        given()
          .header("X-API-KEY", "your-api-key")
          .when().get("/v1/bc/btc/mainnet/blocks/latest")
          .then()
          .statusCode(200)
          .body(is("hello"));
    }
}