package com.studentapp.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by watchmaster on 9/25/17.
 */
public class TestBase {

    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://localhost:8080/student";
    }
}
