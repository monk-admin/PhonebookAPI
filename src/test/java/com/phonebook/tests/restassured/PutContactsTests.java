package com.phonebook.tests.restassured;

import com.phonebook.dto.ContactDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutContactsTests extends TestBase{

    String id;


    @BeforeMethod
    public void precondition(){

        ContactDto contactDto = ContactDto.builder()
//                .id("7a39acbf-bba5-4bf4-b131-7b76c95baab0")
                .name("John")
                .lastName("Wick")
                .email("wick@gm.com")
                .phone("077344567891")
                .address("LA")
                .description("Killer")
                .build();

        String message = given()
                .header(AUTHORIZATION, TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .extract().path("message");
        System.out.println(message);
        String[] split = message.split(": ");
        id = split[1];
    }

    @Test
    public void updateContactSuccessTest(){

        ContactDto contactDto = ContactDto.builder()
                .id(id)
                .name("Morgan")
                .lastName("Freeman")
                .email("freeman@gm.com")
                .phone("01777777891")
                .address("Barcelona")
                .description("Actor")
                .build();

        String message = given()
                .header(AUTHORIZATION, TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .when()
                .put("contacts/")
                .then()

                .assertThat().statusCode(200)
                .extract().path("message");
        System.out.println(message);
    }

}
