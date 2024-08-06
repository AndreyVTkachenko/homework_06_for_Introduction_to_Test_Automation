package ru.gb;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование API Location API")
public class GetLocationTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест GetLocationTest - получение местоположения")
    @Description("Данный тест предназначен для получения местоположения Barnaul")
    @Link("http://dataservice.accuweather.com/locations/v1/cities/autocomplete")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getGetLocation() {

        List<Location> result = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Barnaul")
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.get(0).getLocalizedName().contains("Barnaul"));
    }
}
