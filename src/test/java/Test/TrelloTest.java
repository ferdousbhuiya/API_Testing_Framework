package Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class TrelloTest extends BaseTest{

    @Test
    public void verifyToCreateBoard()
    {
        extentReport.createTestCase("Verify to Create A Board");

        String key = configProperties.getProperty("APIkey");
        String token = configProperties.getProperty("APItoken");
        String name = "New list at 26 November after 12:35 pm";
        RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name)
                .when().post("/1/boards/")
                .then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void verifytoCreateList()
    {

        extentReport.createTestCase("Verify Create list");

        String key = configProperties.getProperty("APIkey");
        String token = configProperties.getProperty("APItoken");
        String name = "New list after 12:30 pm";

        String ProductId = RestAssured.given().contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name).when().post("/1/boards/")
                .then().extract().path("id");


        RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "List by automation 26 Nov.").queryParam("idBoard",ProductId)
                .when().post("/1/lists")
                .then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void verifytoCreateCard()
    {
        extentReport.createTestCase("Verify Create A Card");

        String key = configProperties.getProperty("APIkey");
        String token = configProperties.getProperty("APItoken");
        String name = "New list after 12:30 pm, Serial 1";

        //Create A Board and Take the id#
        String BoardId = RestAssured.given().contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name).when().post("/1/boards/")
                .then().log().all().extract().path("id");

        //Create a List and take the id#
        String ListID = RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "List by automation 26 Nov. Serial 1").queryParam("idBoard",BoardId)
                .when().post("/1/lists")
                .then().log().all().extract().path("id");

        // Create a card and verify
        RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Card by automation 26 Nov. Serial 1").queryParam("idList",ListID)
                .when().post("/1/cards")
                .then().log().all().assertThat().statusCode(200);
    }
    @Test
    public void verifytoDelteACard()
    {
        extentReport.createTestCase("Verify to Delte A Card");

        String key = configProperties.getProperty("APIkey");
        String token = configProperties.getProperty("APItoken");
        String name = "New list after 12:30 pm, Serial 2";

        //Create A Board and Take the id#
        String BoardId = RestAssured.given().contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name).when().post("/1/boards/")
                .then().extract().path("id");

        //Create a List and take the id#
        String ListID = RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "List by automation 26 Nov. Serial 2").queryParam("idBoard",BoardId)
                .when().post("/1/lists")
                .then().extract().path("id");

        // Create a card and take the id#
        String CardId = RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Card by automation 26 Nov. Serial 2").queryParam("idList",ListID)
                .when().post("/1/cards")
                .then().extract().path("id");

        //Delete the created card
        RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Card by automation 26 Nov. Updated Card")
                .pathParam("id", CardId)
                .when().delete("/1/cards/{id}")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void verifytoUpdateACard()
    {
        extentReport.createTestCase("Verify to Update A Card");

        String key = configProperties.getProperty("APIkey");
        String token = configProperties.getProperty("APItoken");
        String name = "New list after 12:30 pm, Serial 2";

        //Create A Board and Take the id#
        String BoardId = RestAssured.given().contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name).when().post("/1/boards/")
                .then().extract().path("id");

        //Create a List and take the id#
        String ListID = RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "List by automation 26 Nov. Serial 2").queryParam("idBoard",BoardId)
                .when().post("/1/lists")
                .then().extract().path("id");

        // Create a card and take the id#
        String CardId = RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Card by automation 26 Nov. Serial 2").queryParam("idList",ListID)
                .when().post("/1/cards")
                .then().extract().path("id");

        //Update the created card
        RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Card by automation 26 Nov. Updated Card")
                .pathParam("id", CardId)
                .when().put("/1/cards/{id}")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void verifytoDelteACardCofirmation()
    {
        extentReport.createTestCase("Verify to Delte A Card");

        String key = configProperties.getProperty("APIkey");
        String token = configProperties.getProperty("APItoken");
        String name = "New list after 12:30 pm, Serial 2";

        //Create A Board and Take the id#
        String BoardId = RestAssured.given().contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name).when().post("/1/boards/")
                .then().extract().path("id");

        //Create a List and take the id#
        String ListID = RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "List by automation 26 Nov. Serial 2").queryParam("idBoard",BoardId)
                .when().post("/1/lists")
                .then().extract().path("id");

        // Create a card and take the id#
        String CardId = RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Card by automation 26 Nov. Serial 2").queryParam("idList",ListID)
                .when().post("/1/cards")
                .then().extract().path("id");

        //Delete the created card
        RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Card by automation 26 Nov. Updated Card")
                .pathParam("id", CardId)
                .when().delete("/1/cards/{id}")
                .then().assertThat().statusCode(200);

        RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Card by automation 26 Nov. Updated Card")
                .pathParam("id", CardId)
                .when().get("/1/cards/{id}")
                .then().assertThat().statusCode(404);

    }
    @Test
    public void verifytoGetABoard()
    {

        extentReport.createTestCase("Verify Create list");

        String key = configProperties.getProperty("APIkey");
        String token = configProperties.getProperty("APItoken");
        String name = "New list after 12:30 pm";

        String BoardId = RestAssured.given().contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", name).when().post("/1/boards/")
                .then().extract().path("id");


        RestAssured.given().log().all().contentType(ContentType.JSON).queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", "Card by automation 26 Nov. Updated Card")
                .pathParam("id", BoardId)
                .when().get("/1/boards/{id}")
                .then().assertThat().statusCode(200);
    }




}
