package com.xuanwugate.blockchain.bitcoin;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import com.xuanwugate.authentication.jwt.TokenUtils;

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
        Map<String, String> addressInfo = generateAddressEndpoint(token);
        Assertions.assertTrue(addressInfo == null);
    }

    public Map<String, String>  generateAddressEndpoint(String token){
        final Response res = given()
        .auth().oauth2(token)
        .post("http://localhost:8080/v1/bc/btc/testnet/address").prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        final Map<String, String> payload = bodyJson.getMap("payload",String.class,String.class);
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
        return payload;
    }
}