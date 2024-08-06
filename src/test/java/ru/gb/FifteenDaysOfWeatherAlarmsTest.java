package ru.gb;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

@Epic("Тестирование проекта accuweather.com")
@Feature("Тестирование API Weather API")
public class FifteenDaysOfWeatherAlarmsTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест FifteenDaysOfWeatherAlarmsTest (негативный) - отображение 15 дней погодных условий")
    @Description("Негативный тест - проверка отображения погодных условий на 15 дней")
    @Link("http://dataservice.accuweather.com/alarms/v1/15day")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getFifteenDaysOfWeatherAlarms() {

        given()
                .when()
                .queryParam("apikey", getApiKey())
                .get(getBaseUrl() + "/alarms/v1/15day//290421")
                .then()
                .statusCode(401)
                .time(lessThan(2000L))
                .statusLine("HTTP/1.1 401 Unauthorized");
    }
}
