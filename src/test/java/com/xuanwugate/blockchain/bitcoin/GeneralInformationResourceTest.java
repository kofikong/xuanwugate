package com.xuanwugate.blockchain.bitcoin;

import com.xuanwugate.authentication.jwt.TokenUtils;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

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
        given().auth()
          .oauth2(token)
          .when().get("/v1/bc/btc/mainnet/info")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testGetBlockByHashEndpoint() {
        given().auth()
          .oauth2(token)
          .when().get("/v1/bc/btc/mainnet/blocks/00000000c937983704a73af28acdec37b049d214adbda81d7e2a3dd146f6ed09")
          .then()
          .statusCode(200)
          .body(is("hello"));
    }

    @Test
    public void testGetBlockByHeightEndpoint() {
        given().auth()
          .oauth2(token)
          .when().get("/v1/bc/btc/mainnet/blocks/564349")
          .then()
          .statusCode(200)
          .body(is("hello"));
    }

    @Test
    public void testGetBlockLasterEndpoint() {
        given().auth()
          .oauth2(token)
          .when().get("/v1/bc/btc/mainnet/blocks/latest")
          .then()
          .statusCode(200)
          .body(is("hello"));
    }
}