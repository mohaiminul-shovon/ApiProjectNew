package Base;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginWithPhoneNo {

    @Test(priority = 1)
    public static String getUserId() {
        JSONObject payload = new JSONObject();
        payload.put("userType","owner");
        payload.put("phoneNo","01533387952");
        payload.put("deviceId","Nice");

        return RestAssured
                .given()
                .header("lat","23.45")
                .header("lng","90.46")
                .header("Content-Type","application/json")
                .header("source","tower")
                .body(payload.toString())
                .when()
                .post("https://n.trucklagbe.com/tl_login/106/loginWithPhoneNo")
                .then()
                .extract().response().getBody().jsonPath().get("userId");
    }
    @Test(priority = 2)
    public static String tokenGeneration(String user_id){
        JSONObject payload = new JSONObject();
        payload.put("userID",user_id);
        payload.put("userType","owner");

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .with()
                .body(payload.toString())
                .when()
                .post("https://n.trucklagbe.com/tl_login/106/authToken")
                .then()
                .extract().response().body().jsonPath().get("accessToken");

    }

    public static void main(String[] args) {
        
        String user_id = getUserId();
        
        String JWT = tokenGeneration(user_id);

        System.out.println(JWT);
    }
}


