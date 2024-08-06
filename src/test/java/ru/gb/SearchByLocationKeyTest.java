package ru.gb;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование API Location API")
public class SearchByLocationKeyTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест SearchByLocationKeyTest - поиск по ключу местоположения")
    @Description("Данный тест предназначен для проверки поиска по ключу 291662")
    @Link("http://dataservice.accuweather.com/locations/v1")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getSearchByLocationKey() {

        Location location = given()
                .queryParam("apikey", getApiKey())
                .pathParam("location", 291662)
                .when()
                .get(getBaseUrl() + "/locations/v1/{location}")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Location.class);

        Assertions.assertEquals("291662", location.getKey());
        Assertions.assertEquals("Barnaul", location.getLocalizedName());
        Assertions.assertEquals("Russia", location.getCountry().getLocalizedName());
        Assertions.assertEquals("Altay", location.getAdministrativeArea().getLocalizedName());
    }
}
