package ru.gb;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование API Weather API")
public class WeatherFiveDaysTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Тест WeatherFiveDaysTest - отображение погоды на пять дней")
    @Description("Данный тест предназначен для проверки получения погоды на пять дней")
    @Link("http://dataservice.accuweather.com/forecasts/v1/daily/5day")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getWeatherFiveDays(){
        Weather weather = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/5day/291662")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().as(Weather.class);

        Assertions.assertEquals(5, weather.getDailyForecasts().size());
    }
}
