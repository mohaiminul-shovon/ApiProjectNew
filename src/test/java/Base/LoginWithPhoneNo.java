package Base;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
//import io.restassured.response.ResponseBody;
import org.json.JSONObject;
import org.testng.annotations.Test;

//import static java.lang.Integer.parseInt;


public class LoginWithPhoneNo {

    public String ownerUserId;
    public int  userId;
    public String jwt;
    public String token;
    public int versionCode = 106;

    String loginWithPhoneNo = "https://n.trucklagbe.com/tl_login/"+versionCode+"/loginWithPhoneNo";
    String authorization = "https://n.trucklagbe.com/tl_login/"+versionCode+"/authToken";
    String getUserInfoFromJWT = "https://n.trucklagbe.com/appApi/"+versionCode+ "/userInfoFromJwt";

    JSONObject payload = new JSONObject();


    @Test(priority = 1) //loginWithPhoneNumber
    public void getUserId() {
        JSONObject payload = new JSONObject();
        payload.put("userType","shipper"); //give user type
        payload.put("phoneNo","01844526840"); //give user phone number
        payload.put("deviceId","Nice");
        this. ownerUserId = RestAssured
                .given()
                .header("lat","23.45")
                .header("lng","90.46")
                .contentType("application/json")
                .header("source","tower")
                .body(payload.toString())
                .when()
                .post(loginWithPhoneNo)
                .then().log().body()
                .extract().response().body().jsonPath().getString("userId[0]");


        System.out.println((ownerUserId));

    }

    @Test(priority = 2) //authToken
    public  void tokenGeneration(){


        JSONObject payload = new JSONObject();
        payload.put("userID",Integer.parseInt(ownerUserId));
        payload.put("userType","owner");

         jwt = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .with()
                .body(payload.toString())
                .when()
                .post(authorization)
                .then().log().body()
               .extract().response().body().jsonPath().get("accessToken");

         this.token = "Bearer"+ " "+ jwt;

         System.out.println("Authorization Token: "+token);

    }
}


