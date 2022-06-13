package TestCases;

import groovyjarjarpicocli.CommandLine;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;

public class ExampleClass{

    @Test(priority = 1)
    public static String getShipperInfo() {

        JSONObject payload = new JSONObject();
        payload.put("shipperData", "379800");
        RestAssured.baseURI = "https://n.trucklagbe.com/adminGet/106";
        RequestSpecification httpRequest = RestAssured.given().contentType("application/json").
                header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOjM3NTgzMCwidXNlcnR5cGUiOiJzaGlwcGVyIiwidXNlcm5hbWUiOiJUZXN0X0ZhYmloYV9RQSIsInBob25lIjoiMDE5MjA4MDI4NjAiLCJ1c2VyRGV0YWlsc0lkIjozNzk4MDAsInJlZnJlc2hLZXkiOiI4MXJTczdIejNidkFyRURpQlgza09nPT0iLCJpYXQiOjE2NTUxMTg4NjAsImV4cCI6MTY1NzcxMDg2MH0.MaljjF600rr9lIB-0Oa-LIzm_mb78cQMTeWQZ853-Dc")
                .with().body(payload.toString());


        Response response = httpRequest.request(Method.POST,"/getShipperInfo");

        ResponseBody responseBody = response.getBody();
        // String responseBody = response.getBody().asString();
        String userId = responseBody.jsonPath().getString("shipperInfo.user_id");

        return userId;
    }

    @Test(priority = 2)
    public static String tokenGeneration(String getUserId){

        String user_id = getUserId;
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
        String getUserId = getShipperInfo();
        System.out.println(getUserId);
        String getToken = tokenGeneration(getUserId);
        System.out.println(getToken);
    }

}
