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
public class ListOfIndexGroupsTest extends AccuweatherAbstractTest {

    @Test
    @DisplayName("Тест ListOfIndexGroupsTest - отображение списка индексных групп")
    @Description("Данный тест предназначен для проверки получения списка индексных групп")
    @Link("http://dataservice.accuweather.com/indices/v1/daily/groups")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Андрей Ткаченко")
    void getListOfIndexGroups() {

        List<MetaData> response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/indices/v1/daily/groups")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000L))
                .extract()
                .body().jsonPath().getList(".", MetaData.class);

        Assertions.assertEquals(47, response.size());
        Assertions.assertEquals("All API", response.get(0).getName());
    }
}
