package com.xuanwugate.authentication.jwt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.startsWith;
// import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;


/**
 * GenerateToken
 */
@QuarkusTest
public class GenerateTokenResourceTest {

    public String getJWTGenerateToken(){
        final Response res = given().header("X-API-KEY", "your-api-key").get("http://localhost:8080/v1/authentication/jwt/generatetoken").prettyPeek();
        final String token_id = res.getBody().jsonPath().getString("Token");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(token_id != null && !token_id.isEmpty());
        return token_id;
    }
    @Test
    public void testJWTGenerateTokenEndpoint() {
        // given().header("X-API-KEY", "your-api-key").when().get("/v1/authentication/jwt/generatetoken").then().statusCode(200).body(is("hello"));
        // given().header("X-API-KEY", "your-api-key").when().get("http://localhost:8080/v1/authentication/jwt/generatetoken").then().statusCode(200).body(containsString("Token"));
        getJWTGenerateToken();
    }
}