package Utils;

import groovyjarjarpicocli.CommandLine;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class generateJWT {
    public void main(String[] aregs){
        loginWithphoneNo();
    }

    public static String loginWithphoneNo(){
        JSONObject payload = new JSONObject();
        payload.put("userType","shipper");
        payload.put("phoneNo","01844526840");
        payload.put("deviceId","Nice");

        Response res = given()
                .header("lat","23.45")
                .header("lng","90.46")
                .header("source","tower")
                .contentType("application/json")
                .with().body(payload.toString())
                .when()
                .post("https://n.trucklagbe.com/tl_login/106/loginWithPhoneNo")
                .then()
                .log().body()
                .extract().response();

        String userId = res.jsonPath().get("userId").toString();
        System.out.println(userId);
        return userId;



    }
}
