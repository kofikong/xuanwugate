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
public class TransactionResourceTest {
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
    public void testGetTransactionDetailsByTxidEndpoint() {
        String txid = "4c6197a86f1b393a4d3a98b258047a9af4a609daec0604e9360b36d810364c28";
        final Response res = given()
        .auth().oauth2(token)
        .get("/v1/bc/btc/testnet/txs/txid/"+txid).prettyPeek();
        final JsonPath bodyJson = res.getBody().jsonPath();
        //Response Body, maybe it well null.
        final String payload = bodyJson.getString("payload");
        //Response Error
        final String meta = bodyJson.getString("meta");
        res.then().statusCode(equalTo(200));
        Assertions.assertTrue(payload != null,meta);
    }
}