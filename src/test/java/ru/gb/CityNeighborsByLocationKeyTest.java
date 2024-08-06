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
public class CityNeighborsByLocationKeyTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест CityNeighborsByLocationKeyTest - отображение соседних городов по ключу 291662")
    @Description("Данный тест предназначен для проверки получения соседних городов местоположения Barnaul")
    @Link("http://dataservice.accuweather.com/locations/v1/cities/neighbors")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getCityNeighborsByLocationKey() {
        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/cities/neighbors/291662")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(10, response.size());
        Assertions.assertEquals("Novoaltaysk", response.get(0).getLocalizedName());
    }
}
