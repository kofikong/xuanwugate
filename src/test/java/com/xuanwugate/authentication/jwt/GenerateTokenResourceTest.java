package com.xuanwugate.authentication.jwt;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


/**
 * GenerateToken
 */
@QuarkusTest
public class GenerateTokenResourceTest {

    @Test
    public void testJWTGenerateToken() {
        given()
                .header("X-API-KEY", "your-api-key")
                .when().get("/v1/authentication/jwt/generatetoken")
                .then().statusCode(200);

        JsonPath jsonPath = RestAssured.given().header("X-API-KEY", "your-api-key")
                .when().get("/v1/authentication/jwt/generatetoken").thenReturn().jsonPath();
        String token_id = jsonPath.getString("Token");
        Assertions.assertTrue(token_id != null && !token_id.isEmpty());
    }
}