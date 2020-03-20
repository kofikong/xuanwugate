package com.xuanwugate.blockchain.bitcoin;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import com.alibaba.fastjson.JSONObject;
import com.xuanwugate.authentication.jwt.GenerateTokenResourceTest;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Map;

@QuarkusTest
public class AddressResourceTest {
    @Test
    public void testGenerateAddressEndpoint() {
        GenerateTokenResourceTest token = new GenerateTokenResourceTest();
        final String token_id = token.getJWTGenerateToken();
        Map<String, String> addressInfo = generateAddressEndpoint(token_id);
        Assertions.assertTrue(addressInfo == null);
    }

    public Map<String, String>  generateAddressEndpoint(String token_id){
        final Response res = given()
        .header("X-API-KEY", "your-api-key")
        .header("Authorization", "Bearer "+token_id)
        .post("http://localhost:8080/v1/bc/btc/testnet/address").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final Map<String, String> payload = bodyJson.getMap("payload",String.class,String.class);
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
        // JSONObject jsonObj = JSONObject.parseObject(payload);
        return payload;
    }
}