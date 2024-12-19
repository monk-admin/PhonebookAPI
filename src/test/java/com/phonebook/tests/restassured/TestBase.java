package com.phonebook.tests.restassured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static final String TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidWtyQGhlbGxvLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNzM1MTE4MDUxLCJpYXQiOjE3MzQ1MTgwNTF9.3Yu_KQDQaIAxie1ShSxlQUgWmakFuCmr7ACFZGhw2RI\n";

    public static final String AUTHORIZATION="Authorization";

    @BeforeMethod
    public void init(){
        RestAssured.baseURI="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath="v1";
    }
}
