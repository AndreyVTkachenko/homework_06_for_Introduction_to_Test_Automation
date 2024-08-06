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
public class CurrentConditionsTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест CurrentConditionsTest - отображение текущих условий")
    @Description("Данный тест предназначен для проверки получения текущих погодный условий")
    @Link("http://dataservice.accuweather.com/currentconditions/v1/15")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
        void getCurrentConditions() {

        List<CurrentCondition> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/currentconditions/v1/15")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", CurrentCondition.class);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals("http://www.accuweather.com/en/jp/oga-shi/16/current-weather/16?lang=en-us",
                response.get(0).getMobileLink());
    }
}
