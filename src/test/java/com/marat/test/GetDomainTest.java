package com.marat.test;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.marat.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

@Feature("Request to get domain")
public class GetDomainTest {

    @DisplayName("Get domain")
    @AllureId("18463")
    @Owner(value="Marat")
    @Test
    void getDomainRequest() {
        given()
                .filter(customLogFilter().withCustomTemplates())
                .contentType(ContentType.JSON)
                .get("https://api.mail.tm/domains")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("schema/GetDomainSchema"))
                .body("@id", is("/domains"));
    }
}
