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
public class CountryListTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест CountryListTest - отображение списка стран")
    @Description("Данный тест предназначен для проверки получения списка стран")
    @Link("http://dataservice.accuweather.com/locations/v1/countries/ARC")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getCountryList() {

        List<Country> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/countries/ARC")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Country.class);

        Assertions.assertEquals(2, response.size());
        Assertions.assertEquals("Greenland", response.get(0).getLocalizedName());
        Assertions.assertEquals("Iceland", response.get(1).getLocalizedName());
    }
}
