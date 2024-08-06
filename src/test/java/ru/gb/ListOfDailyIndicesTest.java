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
@Owner("Андрей Ткаченко")
public class ListOfDailyIndicesTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест ListOfDailyIndicesTest - отображение списка ежедневных индексов")
    @Description("Данный тест предназначен для проверки получения списка ежедневных индексов")
    @Link("http://dataservice.accuweather.com/indices/v1/daily")
    @Severity(SeverityLevel.NORMAL)
    void getListOfDailyIndices() {

        List<MetaData> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/indices/v1/daily")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", MetaData.class);

        Assertions.assertEquals(118, response.size());
        Assertions.assertEquals("Air Conditioning Index", response.get(0).getName());
    }
}
