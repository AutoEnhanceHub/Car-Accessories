package car_accessories;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class LoginDiffblueTest {
    /**
     * Method under test: {@link Login#setRoles()}
     */
    @Test
    public void testSetRoles() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Act
        login.setRoles();

        // Assert
        assertEquals(0, login.getRoles());
    }

    /**
     * Method under test: {@link Login#setRoles()}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSetRoles2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.equalsIgnoreCase(String)" because "type" is null
        //       at car_accessories.Login.setRoles(Login.java:101)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou"));

        // Act
        login.setRoles();
    }

    /**
     * Method under test: {@link Login#setRoles()}
     */
    @Test
    public void testSetRoles3() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou", "Type"));

        // Act
        login.setRoles();

        // Assert
        assertEquals(-1, login.getRoles());
    }

    /**
     * Method under test: {@link Login#setRoles()}
     */
    @Test
    public void testSetRoles4() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou", "Customer"));

        // Act
        login.setRoles();

        // Assert
        assertEquals(1, login.getRoles());
    }

    /**
     * Method under test: {@link Login#setRoles()}
     */
    @Test
    public void testSetRoles5() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou", "Installer"));

        // Act
        login.setRoles();

        // Assert
        assertEquals(2, login.getRoles());
    }
}
