package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.equalTo;

public class OnlinerTest {
    @Test
    public void getCurrencyUsdRateTest() {
        given()
                .log().all()
                .when()
                .get("https://www.onliner.by/sdapi/kurs/api/bestrate?currency=USD&type=nbrb")
                .then()
                .log().all()
                .statusCode(HTTP_OK);
    }

    @Test
    public void getCurrencyEurRateTest2() {
        Response response = (Response) given()
                .log().all()
                .when()
                .get("https://www.onliner.by/sdapi/kurs/api/bestrate?currency=EUR&type=nbrb")
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .body("amount",equalTo("3,2773"));
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }
}
