package com.studentapp.serenity;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.rest.stubs.RequestSpecificationStub;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

/**
 * Created by watchmaster on 9/29/17.
 */
public class StudentSerenitySteps {

    /**
     * reusable method to create a new student
     * The function returns ValidatableResponse
     * Using @Step annotation in reusable method lets us print reusable method as a step in serenity report
     */

    @Step("Creating student with firstName: {0}, lastName:{1}, programme:{2}, email:{3}, courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String programme, String email, List<String> courses){

        StudentClass student =new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                //.contentType(ContentType.JSON)
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .post()
                .then()
                .log()
                .all();
    }

    /**
     * reusable method to get student details by firstName
     * Whatever firstName is provided as an argument, the information related to it will be returned as hashmap
     */

    @Step("Getting the student information with firstName: {0}")
    public HashMap<String,Object> getStudentInfoByFirstName(String firstName){

        String p1="findAll{it.firstName=='";
        String p2="'}.get(0)";

        return SerenityRest.rest().given() // .rest() has been added to print logs in report
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .path(p1+firstName+p2);
    }

    /**
     * reusable method to update a  student
     * The function returns ValidatableResponse
     */

    @Step("Updating student information with studentid:{0}, firstName: {1}, lastName:{2}, email:{3}, programme:{4}, courses:{5}")
    public ValidatableResponse updateStudent(int studentid, String firstName, String lastName, String email, String programme, List<String> courses){

        StudentClass student =new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                //.contentType(ContentType.JSON)
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .put("/"+studentid)
                .then();
    }

    /**
     * reusable method to delete student details by studentid
     */

    @Step("deleting the student information with id: {0}")
    public void deleteStudent(int studentId){

        SerenityRest.rest().given() // .rest() has been added to print logs in report
                .when()
                .delete("/"+studentId);
    }

    /**
     * reusable method to delete student details by studentid
     */

    @Step("Getting the student information with id: {0}")
    public ValidatableResponse getStudentById(int studentId){

        return SerenityRest.rest().given() // .rest() has been added to print logs in report
                .when()
                .get("/"+studentId)
                .then();
    }

    @Step
    public HashMap<String, Object> getStudentInfoByEmailId(String email) {

        String p1 = "findAll{it.email=='";
        String p2 = "'}.get(0)";
        return SerenityRest
                .rest().given().when().get("/list").then().extract()
                .path(p1 + email + p2);
    }
}
