package ru.gb;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование API Weather API")
public class WeatherTenDaysTest extends AccuweatherAbstractTest {


    @Test
    @DisplayName("Тест WeatherTenDaysTest (негативный) - отображение погоды на десять дней")
    @Description("Негативный тест - проверка получения погоды на 10 дней")
    @Link("http://dataservice.accuweather.com/forecasts/{version}/daily/10day")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getWeatherTenDays_shouldReturn401() {
        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("location", 291662)
                .when()
                .get(getBaseUrl() + "/forecasts/{version}/daily/10day/{location}")
                .then()
                .statusCode(401);
    }
}
