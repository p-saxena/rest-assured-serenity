package com.studentapp.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

/**
 * this class has 2 methods. first method ie-getGenericRequestSpec returns request specification object.
 * request specification object is a set of pre-requisite conditions before sending a request object
 * second method ie getGenericResponseSpec returns response specification object which is a set of pre-defined conditions which can be used to validate certain criteria in response
 */
public class ReusableSpecifications {

    public static RequestSpecBuilder rspec;
    public static RequestSpecification requestSpecification;

    public static ResponseSpecBuilder respec;
    public static ResponseSpecification responseSpecification;

    /**
     * rspec object is used to set request specification
     * requestSpecification is the final request specification object after all values have been set
     * The advantage of setting request specification is if we have few parameters to set like multiple header/cookies values, they can be done here once and use multiple times in other tests
     */
    public static RequestSpecification getGenericRequestSpec(){

        rspec = new RequestSpecBuilder();
        rspec.setContentType(ContentType.JSON);
        requestSpecification = rspec.build();
        return requestSpecification;

    }

    public static ResponseSpecification getGenericResponseSpec(){
        respec = new ResponseSpecBuilder();
        respec.expectHeader("Content-Type","application/json;charset=UTF-8");
        respec.expectHeader("Transfer-Encoding","chunked");
        respec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
        responseSpecification = respec.build();
        return responseSpecification;

    }
}
