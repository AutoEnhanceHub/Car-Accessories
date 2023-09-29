package carAccessories;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Testing {
    Application obj;
    Testing(){
        obj=new Application();
    }

    @Given("that the user is not logged in")
    public void that_the_user_is_not_logged_in() {
        assertFalse(obj.logged_in);
    }

    @When("the information is valid email is {string} and password is {string}")
    public void the_information_is_valid_email_is_and_password_is(String email, String pass) {



    }

    @Then("user successfully log in")
    public void user_successfully_log_in() {

    }

    @When("the email is invalid email is {string} and password is {string}")
    public void the_email_is_invalid_email_is_and_password_is(String string, String string2) {

    }

    @Then("user failed in log in")
    public void user_failed_in_log_in() {

    }

    @When("the password is invalid email is {string} and password is {string}")
    public void the_password_is_invalid_email_is_and_password_is(String string, String string2) {

    }

    @When("the information are invalid email is {string} and password is {string}")
    public void the_information_are_invalid_email_is_and_password_is(String string, String string2) {

    }

}
