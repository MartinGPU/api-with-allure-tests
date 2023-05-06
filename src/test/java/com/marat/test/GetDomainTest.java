package com.marat.test;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static com.marat.filters.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;

public class GetDomainTest {

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
