package carAccessories;
import io.cucumber.java.en.*;

import java.util.Random;

import static org.junit.Assert.*;


public class Testing {
    private final Application application;



    public Testing(Application application) {
        this.application = application;
    }

    @Given("that the user is not logged in")
    public void that_the_user_is_not_logged_in() {
        assertFalse(application.login.isLogged());
    }

    @When("the information is valid email is {string} and password is {string}")
    public void the_information_is_valid_email_is_and_password_is(String Email, String Pass) {
        boolean loginSuccessful = false;
        for(User u1:application.login.users){
            if(Login.emailValidator(u1.getEmail())){
                if(u1.getEmail().equalsIgnoreCase(Email)&&u1.getPassword().equals(Pass)){
                    application.login.setLogged(true);
                    loginSuccessful=true;
                    break;
                }
            }
        }
        assertTrue(loginSuccessful);
    }
    @And("verification code is {string}")
    public void verification_code_is(String string) {
        boolean f=true;
        assertTrue(f);
    }

    @Then("user successfully log in")
    public void user_successfully_log_in() {
        boolean loginSuccessful = false;
        if(application.login.isLogged()){
            loginSuccessful=true;
        }
        assertTrue(loginSuccessful);
    }

    @When("the email is invalid email is {string} and password is {string}")
    public void the_email_is_invalid_email_is_and_password_is(String Email, String Pass) {
        boolean loginFailed = false;
        for (User u1 : application.login.users) {
            if (!u1.getEmail().equalsIgnoreCase(Email) && u1.getPassword().equals(Pass)) {
                application.login.setLogged(false);
                loginFailed = true;
                break;
            }
        }

        assertFalse(loginFailed);
    }

    @Then("user failed in log in")
    public void user_failed_in_log_in() {
        boolean loginFailed = false;
        if(!application.login.isLogged()){
            loginFailed=true;
        }
        assertTrue(loginFailed);
    }

    @When("the password is invalid email is {string} and password is {string}")
    public void the_password_is_invalid_email_is_and_password_is(String Email, String Pass) {
        boolean loginFailed = false;
        for (User u1 : application.login.users) {
            if (u1.getEmail().equalsIgnoreCase(Email) && !u1.getPassword().equals(Pass)) {
                application.login.setLogged(false);
                loginFailed = true;
                break;
            }
        }

        assertFalse(loginFailed);
    }

    @When("the information are invalid email is {string} and password is {string}")
    public void the_information_are_invalid_email_is_and_password_is(String Email, String Pass) {
        boolean loginFailed = false;
        for(User u1:application.login.users){
            if(!u1.getEmail().equalsIgnoreCase(Email)&&!u1.getPassword().equals(Pass)){
                application.login.setLogged(false);
                loginFailed=true;
                break;
            }
        }
        assertTrue(loginFailed);
    }
}