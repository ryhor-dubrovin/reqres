package tests;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres.*;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.*;


public class ReqResTest {
    private static final String BASE_URL = "https://reqres.in/api/";
    @Test
    public void getListUsersTest() {
        Response response = given()
                .log().all()
                .when()
                .get(BASE_URL + "users?page=2")
                .then()
                .log().all()
                .extract().response();

        String body = response.body().asString();
        ListUniversal listUniversal = new Gson().fromJson(body, ListUniversal.class);
        boolean testResult = (listUniversal.getPage() == 2)
                // && (listUniversal.getPerPage() == 6)
                // && (listUniversal.getTotal() == 12)
                // && (response.statusCode() == HTTP_OK);
        Assert.assertTrue(testResult);
    }
    @Test
    public void getSingleUserTest() {
        Response response = given()
                .log().all()
                .when()
                .get(BASE_URL + "users/2")
                .then()
                .log().all()
                .extract().response();
        String body = response.body().asString();
        SingleUser singleUser = new Gson().fromJson(body, SingleUser.class);
        boolean testResult = (response.statusCode() == HTTP_OK)
                // && (singleUser.getData().getId() == 2)
                // && (singleUser.getSupport().getText().equals(Support.TEXT_MESSAGE));
        Assert.assertTrue(testResult);
    }
    @Test
    public void getSingleUserNotFoundTest() {
        Response response = given()
                .log().all()
                .when()
                .get(BASE_URL + "users/23")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(), HTTP_NOT_FOUND);
    }
    @Test
    public void getListResourceTest() {
        Response response = given()
                .log().all()
                .when()
                .get(BASE_URL + "unknown")
                .then()
                .log().all()
                .extract().response();
        String body = response.body().asString();
        ListUniversal listUniversal = new Gson().fromJson(body, ListUniversal.class);
        int dataSize = listUniversal.getData().toArray().length;
        System.out.println(dataSize);
        boolean testResult = (response.statusCode() == HTTP_OK)
                // && (dataSize == listUniversal.getPerPage());
        Assert.assertTrue(testResult);
    }
    @Test
    public void getSingleResourceTest() {
        Response response = given()
                .log().all()
                .when()
                .get(BASE_URL + "unknown/2")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(),HTTP_OK);
    }
    @Test
    public void getSingleResourceNotFoundTest() {
        given()
                .log().all()
                .when()
                .get(BASE_URL + "unknown/23")
                .then()
                .log().all()
                .statusCode(HTTP_NOT_FOUND);
    }
    @Test
    public void postCreateUserTest() {
        User user = User.builder()
                .name("ryhor")
                .job("qa")
                .build();
        Response response = given()
                .log().all()
                .body(user)
                .when()
                .post(BASE_URL + "users")
                .then()
                .log().all()
                .extract().response();
        Assert.assertEquals(response.statusCode(),HTTP_CREATED);
    }
    @Test
    public void putUpdateTest() {
        User user = User.builder()
                .name("ryhor")
                .job("aqa")
                .build();
        given()
                .body(user)
                .log().all()
                .when()
                .put(BASE_URL + "users/2")
                .then()
                .log().all()
                .statusCode(HTTP_OK);
    }
    @Test
    public void patchUpdateTest() {
        User user = User.builder()
                .name("ryhor")
                .job("aqa")
                .build();
        given()
                .body(user)
                .log().all()
                .when()
                .patch(BASE_URL + "users/2")
                .then()
                .log().all()
                .statusCode(HTTP_OK);
    }
    @Test
    public void deleteUserTest() {
        given()
                .log().all()
                .when()
                .delete(BASE_URL + "users/2")
                .then()
                .log().all()
                .statusCode(HTTP_NO_CONTENT);
    }
    @Test
    public void successfulRegisterTest() {
        User user = User.builder()
                        .email("eve.holt@reqres.in")
                                .password("pistol")
                                        .build();
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .log().all()
                .when()
                .post(BASE_URL + "register")
                .then()
                .log().all()
                .statusCode(HTTP_OK);
    }
    @Test
    public void unSuccessfulRegisterTest() {
        User user = User.builder()
                        .email("sydney@fife")
                                .build();
        given()
                .header("Content-Type","application/json")
                .body(user)
                .log().all()
                .when()
                .post(BASE_URL + "register")
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST);
    }
    @Test
    public void successfulLoginTest() {
        User user = User.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        given()
                .header("Content-Type", "application/json")
                .body(user)
                .log().all()
                .when()
                .post(BASE_URL + "login")
                .then()
                .log().all()
                .statusCode(HTTP_OK);
    }
    @Test
    public void unSuccessfulLoginTest() {
        User user = User.builder()
                .email("peter@klaven")
                .build();
        given()
                .header("Content-Type","application/json")
                .body(user)
                .log().all()
                .when()
                .post(BASE_URL + "login")
                .then()
                .log().all()
                .statusCode(HTTP_BAD_REQUEST);
    }
    @Test
    public void delayedResponseTest() {
        given()
                .log().all()
                .when()
                .get(BASE_URL + "users?delay=3")
                .then()
                .log().all()
                .statusCode(HTTP_OK);
    }
}
