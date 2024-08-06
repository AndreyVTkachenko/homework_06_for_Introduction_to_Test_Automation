package ru.gb;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование API Location API")
public class CitySearchTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест CitySearchTest - поиск города")
    @Description("Данный тест предназначен для проверки поиска города Barnaul")
    @Link("http://dataservice.accuweather.com/locations/v1/cities/search")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getCitySearch() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Barnaul")
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/search")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals("Barnaul", response.get(0).getEnglishName());
    }
}
