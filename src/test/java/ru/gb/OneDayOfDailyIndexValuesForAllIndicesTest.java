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
public class OneDayOfDailyIndexValuesForAllIndicesTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест OneDayOfDailyIndexValuesForAllIndicesTest - ежедневные значения индексов за один день для всех индексов")
    @Description("Данный тест предназначен для проверки получения ежедневных значений индексов за один день для всех индексов")
    @Link("http://dataservice.accuweather.com/indices/v1/daily/1day")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getOneDayOfDailyIndexValuesForAllIndices() {

        List<Index> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/indices/v1/daily/1day/5")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Index.class);

        Assertions.assertEquals(48, response.size());
        Assertions.assertEquals("Flight Delays", response.get(0).getName());
    }
}
