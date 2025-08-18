package integration.core.util;

import integration.core.properties.TestProperties;
import integration.user.model.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;

import static integration.core.constant.AppEndpoints.*;
import static io.restassured.RestAssured.given;

public class ApiUtils {

    @Step("Удалить созданого пользователя")
    public static void deleteUser(User user) {
        RestAssured.baseURI = TestProperties.getAppUrl() + API_ENDPOINT;
        Response response = given()
                .contentType(String.valueOf(ContentType.JSON))
                .body(user)
                .when()
                .post(USER_AUTH_ENDPOINT);
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            String accessToken = response.jsonPath().getString("accessToken");
            user.setAccessToken(accessToken);
        }
        if (user.getAccessToken() != null) {
            given()
                    .header(HttpHeaders.AUTHORIZATION, user.getAccessToken())
                    .contentType(String.valueOf(ContentType.JSON))
                    .body(user)
                    .when()
                    .post(USER_DELETE_ENDPOINT);
        }
    }

    @Step("Регистрация пользователя")
    public static void registerUser(User user) {
        given()
                .contentType(String.valueOf(ContentType.JSON))
                .body(user)
                .when()
                .post(USER_REGISTER_ENDPOINT);
    }
}
