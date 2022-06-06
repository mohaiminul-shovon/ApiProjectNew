package Base;


import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class loginWithPhoneNo {
    public static void main(String[] args) {
        System.out.println(getUserId());
    }
    public static String getUserId() {
        JSONObject payload = new JSONObject();
        payload.put("userType","owner");
        payload.put("phoneNo","01533387952");
        payload.put("deviceId","Nice");

        Response res = given()
                .header("lat","23.45")
                .header("lng","90.46")
                .header("Content-Type","application/json")
                .header("source","tower")
                .body(payload)
                .when()
                .post("https://n.trucklagbe.com/tl_login/106/loginWithPhoneNo")
                .then()
                .log().body()
                .extract().response();
        String response = res.toString();
        System.out.println(response);
        return response;


    }
}


