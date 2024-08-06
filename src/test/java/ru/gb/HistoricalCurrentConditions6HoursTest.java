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
@Feature("Тестирование API Weather API")
public class HistoricalCurrentConditions6HoursTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест HistoricalCurrentConditions6HoursTest - история текущих условий за последние 6 часов")
    @Description("Данный тест предназначен для проверки получения истории текущих условий за последние 6 часов")
    @Link("http://dataservice.accuweather.com/currentconditions/v1/5/historical")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getHistoricalCurrentConditions6Hours() {

        List<Historical> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/currentconditions/v1/5/historical")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Historical.class);

        Assertions.assertEquals(6, response.size());
        Assertions.assertEquals("http://www.accuweather.com/en/gr/logos/2285860/current-weather/2285860?lang=en-us", response.get(0).getLink());
    }
}
