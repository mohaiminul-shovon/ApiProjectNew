package TestCases;

import Base.LoginWithPhoneNo;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class expressTripFinish  extends LoginWithPhoneNo {

        public String shipperData;
        public int shipperId;
        public String shipperInfo = "https://n.trucklagbe.com/adminGet/"+versionCode+"/shipperInfo";

        JSONObject payload = new JSONObject();
        @Test(priority = 3)
        public void shipperInfo(){
                payload.put("shipperData", "01844526840");
                 this.shipperData= RestAssured.given()
                        .contentType("application/json")
                        .header("Authorization",this.token)
                        .with()
                        .body(payload.toString())
                        .when()
                        .post(shipperInfo)
                        .then()
                        .log().body()
                .extract().response().body().jsonPath().getString("shipperInfo[0].id");
                 //shipperId = Integer.parseInt(shipperData);
                 System.out.println(shipperData);


        }





}
