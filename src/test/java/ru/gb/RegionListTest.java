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
public class RegionListTest extends AccuweatherAbstractTest{

    @Test
    @DisplayName("Тест RegionListTest - список регионов")
    @Description("Данный тест предназначен для проверки получения списка регионов")
    @Link("http://dataservice.accuweather.com/locations/v1/regions")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getRegionList() {

        List<Region> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/locations/v1/regions")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", Region.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Africa", response.get(0).getLocalizedName());
    }
}
