package car_accessories;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(Cucumber.class)
@CucumberOptions(features="Features",plugin={"html:target/cucumber/wikipedia.html"},monochrome=true,snippets=SnippetType.CAMELCASE,glue={"car_accessories"})


public class AcceptanceTest {
    @Test
    public void testLogin() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Act and Assert
        assertFalse(login.login());
        assertFalse(login.validEmail);
    }

    /**
     * Method under test: {@link Login#login()}
     */
    @Test
    public void testLogin2() {
        // Arrange
        Login login = new Login(new User("iloveyou", "iloveyou"));

        // Act and Assert
        assertFalse(login.login());
        assertFalse(login.validEmail);
    }

    /**
     * Method under test: {@link Login#login()}
     */
    @Test
    public void testLogin3() {
        // Arrange
        Login login = new Login(new User("", "iloveyou"));

        // Act and Assert
        assertFalse(login.login());
        assertFalse(login.validEmail);
    }

    /**
     * Method under test: {@link Login#login()}
     */
    @Test
    public void testLogin4() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "123456"));

        // Act and Assert
        assertFalse(login.login());
        assertFalse(login.validEmail);
    }

    /**
     * Method under test: {@link Login#login()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testLogin5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "car_accessories.User.getEmail()" because "this.u" is null
        //       at car_accessories.Login.login(Login.java:58)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new Login(null)).login();
    }

    /**
     * Method under test: {@link Login#login()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testLogin6() {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        login.addUser(new User("jane.doe@example.org", "iloveyou"));

        // Act
        login.login();
    }

    /**
     * Method under test: {@link Login#login()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testLogin7() {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access the network.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        login.addUser(new User("jane.doe@example.org", "iloveyou"));
        login.addUser(new User("jane.doe@example.org", "iloveyou"));

        // Act
        login.login();
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Application application = new Application();

        // Act
        application.setLoggedIn(true);

        // Assert that nothing has changed
        assertTrue(application.isLoggedIn());
    }
}
