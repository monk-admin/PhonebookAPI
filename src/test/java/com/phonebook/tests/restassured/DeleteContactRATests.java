package com.phonebook.tests.restassured;

import com.phonebook.dto.ContactDto;
import com.phonebook.dto.ErrorDto;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DeleteContactRATests extends TestBase{

    String id;

    @BeforeMethod
    public void precondition(){

        ContactDto contactDto = ContactDto.builder()
                .name("Bob")
                .lastName("Sapp")
                .email("bob@gm.com")
                .phone("12345678900")
                .address("London")
                .description("Hello World!")
                .build();

        String message = given()
                .header(AUTHORIZATION, TOKEN)
                .body(contactDto)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .extract().path("message");
//        System.out.println(message);
//        Contact was added! ID: b847107c-55f7-4b98-bd2b-334f850e32de
        String[] split = message.split(": ");
        id = split[1];
    }

    @Test
    public void deleteContactSuccessTest(){

//        String message =
        given()
                .header(AUTHORIZATION, TOKEN)
                .when()
                .delete("contacts/" + id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", equalTo("Contact was deleted!"));
//                .extract().body().path("message");
//        System.out.println(message);
//        Contact was deleted!
    }

    @Test
    public void deleteContactByWrongId(){
//        ErrorDto errorDto =
        given()
                .header(AUTHORIZATION, TOKEN)
                .when()
                .delete("contacts/b847107c-55f7-4b98-bd2b-334f850e32de")
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message", containsString("not found in your contacts!"));
//                .extract().body().as(ErrorDto.class);
//        System.out.println(errorDto.getMessage());
//        Contact with id: b847107c-55f7-4b98-bd2b-334f850e32de not found in your contacts!
    }
}
