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
public class OneDayOfDailyIndexValuesForASpecificIndexTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест OneDayOfDailyIndexValuesForASpecificIndexTest - ежедневные значения индекса за один день для определенного индекса")
    @Description("Данный тест предназначен для проверки получения ежедневных значений индекса за один день для определенного индекса")
    @Link("http://dataservice.accuweather.com/indices/v1/daily/1day/5")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getOneDayOfDailyIndexValuesForASpecificIndex() {

        List<Index> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/indices/v1/daily/1day/5/8")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Index.class);

        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals("Outdoor Concert Forecast", response.get(0).getName());
    }
}
