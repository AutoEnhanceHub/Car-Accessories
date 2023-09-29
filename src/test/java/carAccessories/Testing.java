package carAccessories;
import io.cucumber.java.en.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class Testing {
    String email,pass,newc,newname,oldname;
    int oldsize;
boolean found=false;
    @Given("I am an admin\\(adding) with email {string} and password {string}")
    public void i_am_an_admin_adding_with_email_and_password(String string, String string2) {
   email=string;
   pass=string2;
   oldsize=Application.categories.size();

    }

    @When("I add a new category with the name {string}")
    public void i_add_a_new_category_with_the_name(String string) {
newc=string;
    }

    @Then("i must scan if the name {string} is exits before")
    public void i_must_scan_if_the_name_is_exits_before(String string) {

for(int i=0;i<Application.categories.size();i++){
   if(string.equals(Application.categories.get(i).name)){
       found=true;
   }
}
}

    @Then("if found i must not add the name {string}")
    public void if_found_i_must_not_add_the_name(String string) {
      if(found)assertTrue(oldsize==Application.categories.size());
    }

    @Then("if not found the category with name {string} must be added")
    public void if_not_found_the_category_with_name_must_be_added(String string) {
        newc=string;
        Application.categories.add(new Category(newc));

    }

    @Then("i must confirm the adding with email {string} and password {string}")
    public void i_must_confirm_the_adding_with_email_and_password(String string, String string2) {
        if(!found) assertTrue((oldsize+1)==Application.categories.size()&&string.equals(email)&&string2.equals(pass));
    }

    @When("I edit the category with the name {string} and change its name to {string}")
    public void i_edit_the_category_with_the_name_and_change_its_name_to(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the category {string} must be edited")
    public void the_category_must_be_edited(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("I am an admin\\(deleting) with email {string} and password {string}")
    public void i_am_an_admin_deleting_with_email_and_password(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("I delete the category with the name {string}")
    public void i_delete_the_category_with_the_name(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the category {string} must be deleted")
    public void the_category_must_be_deleted(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
