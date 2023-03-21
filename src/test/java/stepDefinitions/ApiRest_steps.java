package stepDefinitions;

import PageObjects.ApiRest_PO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.testng.Assert;



public class ApiRest_steps extends ApiRest_PO {
    @Given("User given api url {string}")
    public void user_given_api_url(String url) {
        RestAssured.baseURI = url;
    }

    @Given("Set api endpoint {string}")
    public void set_api_endpoint(String endpoint) {
        RestAssured.basePath = endpoint;

    }
    int user_ID;
    @When("User creates new user with request body {string} {string} {string} {string}")
    public void user_creates_new_user_with_request_body(String name, String email, String gender, String status) {

        response = postMethod(name, email, gender, status);
        response.prettyPrint();
        user_ID = response.jsonPath().getInt("data.id");
        System.out.println("user_ID = " + user_ID);
    }

    @Then("validate the status code {int}")
    public void validate_the_status_code(Integer statuscode) {
        int status = response.getStatusCode();
        Assert.assertEquals(status, statuscode);
    }

    @And("validate the userId is not null")
    public void validate_the_user_id_is_not_null() {
        int id = response.jsonPath().getInt("data.id");
        Assert.assertTrue(id != 0, "id is null");
        System.out.println("UserID : "+ id);
    }

    @And("validate the user name {string}")
    public void validate_the_user_name(String userName) {
        String name = response.jsonPath().getString("data.name");
        Assert.assertEquals(name, userName);
    }

    @And("validate the user email {string}")
    public void validate_the_user_email(String userEmail) {
        String email = response.jsonPath().getString("data.email");
        Assert.assertEquals(email, userEmail);
    }

    @And("validate the user gender {string}")
    public void validate_the_user_gender(String userGender) {
        String gender = response.jsonPath().getString("data.gender");
        Assert.assertEquals(gender, userGender);
    }

    @And("validate the user status {string}")
    public void validate_the_user_status(String userStatus) {
        String status = response.jsonPath().getString("data.status");
        Assert.assertEquals(status, userStatus);
    }
    /* -----GET USER DETAILS------- */

@Then("I get user details")
public void i_get_user_details() {
    response = getMethod();
    response.prettyPrint();

}

    @When("update user details {string} {string} {string}")
    public void updateUserDetails(String name, String gender, String status) {
       response = putMethod(name,gender,status);
       response.prettyPrint();
    }

/* ----------POST  Comment------------ */
    @And("Create a comment {string} and post id {string}")
    public void createACommentAndPostId(String comment, String id) {
        response = postComment(comment,id);
        response.prettyPrint();
    }
    /*---------------DELETE Account------------ */

    @And("I delete the user id {string}")
    public void iDeleteTheUserId(String id) {
        response = deleteUser(id);
        response.prettyPrint();
        System.out.println("User successfully deleted.");
    }


}

