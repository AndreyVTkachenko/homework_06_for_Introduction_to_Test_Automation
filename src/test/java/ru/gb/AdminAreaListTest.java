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
public class AdminAreaListTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест AdminAreaListTest - отображение списка административных территорий")
    @Description("Данный тест предназначен для проверки получения списка административных территорий")
    @Link("https://developer.accuweather.com/accuweather-locations-api/apis")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getAdminAreaList() {

        List<AdministrativeArea> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/adminareas/MEA")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", AdministrativeArea.class);

        Assertions.assertEquals(21, response.size());
        Assertions.assertEquals("Andrijevica", response.get(0).getLocalizedName());
    }
}
