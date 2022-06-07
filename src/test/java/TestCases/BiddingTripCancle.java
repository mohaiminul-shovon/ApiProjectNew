package TestCases;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.lang.System.out;

public class BiddingTripCancle {

    static String jwt = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOjE4MiwidXNlclR5cGUiOiJhZG1pbiIsInVzZXJuYW1lIjoibW9oYWltaW51bCBJc2xhbSIsInBob25lIjoiIiwidGVhbSI6Im5vbmUiLCJyZWZyZXNoS2V5Ijoia3ZpazdTcWcxZ3VwUWpSOEVwU3BIZz09IiwiaWF0IjoxNjU0NDkwODI5fQ.fDLrSnfBLBdXTQURJj93XWpF5Ka_YMvE4A3viPwNNLU";
    String request_id;
    @Test
    public void shipperInfo(){

        //String jwt = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyaWQiOjE4MiwidXNlclR5cGUiOiJhZG1pbiIsInVzZXJuYW1lIjoibW9oYWltaW51bCBJc2xhbSIsInBob25lIjoiIiwidGVhbSI6Im5vbmUiLCJyZWZyZXNoS2V5Ijoia3ZpazdTcWcxZ3VwUWpSOEVwU3BIZz09IiwiaWF0IjoxNjU0NDkwODI5fQ.fDLrSnfBLBdXTQURJj93XWpF5Ka_YMvE4A3viPwNNLU";
        JSONObject payload = new JSONObject();
        payload.put("shipperData","01844526840");

        String userId =
                given()
                .header("Authorization", jwt)
                .contentType("application/json")
                .with()
                .body(payload.toString())
                .when()
                .post("https://n.trucklagbe.com/adminGet/106/shipperInfo")
                .then().statusCode(200)
                .extract().response().body().jsonPath().getString("shipperInfo.user_id");

        out.println("Shipper user Id: " + userId);


    }

    @Test
    public void biddingTripCreate(){
        JSONObject payload = new JSONObject();
        payload.put("userDetailsId", 164623);
        payload.put("shipperType", "customer");
        payload.put("pickAddressBookId", "NA");
        payload.put("dropAddressBookId", "NA");
        payload.put("pickAreaId", 453);
        payload.put("dropAreaId", 620);
        payload.put("pickUnionId", 8192);
        payload.put("dropUnionId", 8231);
        payload.put("pickAdd", 0);
        payload.put("dropAdd", 0);
        payload.put("pickTime", "2022-6-6 19,00,00");
        payload.put("ttCategory", "1007");
        payload.put("ttOpenCover", "open");
        payload.put("truckTypeName", "1 Ton 7 Feet");
        payload.put("productType", "API calls");
        payload.put("shipperBudget", 0);
        payload.put("isCreditTrip", 0);
        payload.put("isCashTrip", 1);
        payload.put("numberOfTrucks", 1);
        payload.put("multiplePickDrop", "");
        payload.put("addtionalService", "");
        payload.put("sourceType", "admin panel");
        payload.put("adminUserName", "mohaiminul Islam");
        payload.put("adminUserId", 182);
        payload.put("tripReferredFrom", "none");
        payload.put("kamId", 0);
        payload.put("kamName", "NA");
        payload.put("kamPhone", "NA");
        payload.put("fullPickAddEngForTripList", "JASHIMUDDIN$UTTARA, DHAKA CITY");
        payload.put("fullPickAddBngForTripList", "জসীমউদ্দিন$উত্তরা, ঢাকা সিটি");
        payload.put("fullDropAddEngForTripList", "KHILGAON RAILGATE$KHILGAON, DHAKA CITY");
        payload.put("fullDropAddBngForTripList", "খিলগাঁও রেলগেট$খিলগাঁও, ঢাকা সিটি");
        payload.put("fullPickAddEngForTripDetails", "JASHIMUDDIN, UTTARA, DHAKA CITY, DHAKA");
        payload.put("fullPickAddBngForTripDetails", "জসীমউদ্দিন, উত্তরা, ঢাকা সিটি, ঢাকা");
        payload.put("fullDropAddEngForTripDetails", "KHILGAON RAILGATE, KHILGAON, DHAKA CITY, DHAKA");
        payload.put("fullDropAddBngForTripDetails", "খিলগাঁও রেলগেট, খিলগাঁও, ঢাকা সিটি, ঢাকা");
        payload.put("pickLat", 23.8592);
        payload.put("pickLng", 90.3889);
        payload.put("dropLat", 23.7436);
        payload.put("dropLng", 90.4267);

        String requestId = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization",jwt)
                .header("lat","0")
                .header("lng", "0")
                .header("source","n/a")
                .with()
                .body(payload.toString())
                .when()
                .post("https://n.trucklagbe.com/adminPost/106/biddingTripCreate")
                .then()
                .statusCode(200)
                .extract().response().body().jsonPath().getString("requestId");



    }
    @Test
    public void bidCreate(){
//        Scanner tripId = new Scanner(System.in);
//        System.out.println("Request ID: ");
//        String requestId = tripId.nextLine(); //take input of request id

        JSONObject payload = new JSONObject();
        payload.put("userDetailsId", 310549);
        payload.put("truckId", 39215);
        payload.put("requestId", "1036193");
        payload.put("bidAmountWithoutCommission", "500");
        payload.put("totalCommission", 50);
        payload.put("bidAmountWithCommission", 550);
        payload.put("isProjectTrip", 0);

        String bidId = RestAssured.given()
                .contentType("application/json")
                .header("Authorization",jwt)
                .header("lat","0")
                .header("lng", "0")
                .header("source","n/a")
                .with()
                .body(payload.toString())
                .when()
                .post("https://n.trucklagbe.com/adminPost/106/ownerNewBidCreate")
                .then()
                .statusCode(200)
                .extract().response().body().jsonPath().getString("bidId");

        out.println("The bid id is: " + bidId);
    }
    @Test
    public void bidAccept(){
       // https://n.trucklagbe.com/adminPost/106/adminBiddingTripAccept

        JSONObject payload = new JSONObject();
        payload.put("userDetailsId", 310549);
        payload.put("bidId", 6184917);
        payload.put("ownerPrice", 550);
        payload.put("tripPrice", 550);
        payload.put("checkBox", "unchecked");
        payload.put("adminUserName", "mohaiminul Islam");
        payload.put("isProjectTrip", 0);
        payload.put("shipperId", 164623);

        RestAssured.given()
                .contentType("application/json")
                .header("Authorization",jwt)
                .header("lat","0")
                .header("lng", "0")
                .header("source","n/a")
                .with()
                .body(payload.toString())
                .when()
                .post("https://n.trucklagbe.com/adminPost/106/adminBiddingTripAccept")
                .then()
                .statusCode(200);

    }
    @Test
    public void verifyTripStatus(){

        JSONObject payload = new JSONObject();
        payload.put("requestID","1036193");

        String status = RestAssured.given()
                .contentType("application/json")
                .header("authorization",jwt)
                .with()
                .body(payload.toString())
                .when()
                .post("https://n.trucklagbe.com/adminGet/106/biddingtripList")
                .then()
                .statusCode(200)
                .extract().response().body().jsonPath().getString("TripRequest.status");
    }






}
