package car_accessories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class LoginDiffblueTest {
    /**
     * Method under test: {@link Login#login()}
     */
    @Test
    public void testLogin() {
        // Arrange

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

    /**
     * Method under test: {@link Login#updateUser(User, User)}
     */
    @Test
    public void testUpdateUser() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        // Act
        boolean actualUpdateUserResult = login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou"));

        // Assert
        User getResult = login.users.get(0);
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertNull(getResult.getType());
        assertTrue(actualUpdateUserResult);
    }

    /**
     * Method under test: {@link Login#updateUser(User, User)}
     */
    @Test
    public void testUpdateUser2() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        login.addUser(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        // Act
        boolean actualUpdateUserResult = login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou"));

        // Assert
        User getResult = login.users.get(4);
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertNull(getResult.getType());
        assertEquals(4, login.userIndex);
        assertTrue(actualUpdateUserResult);
    }

    /**
     * Method under test: {@link Login#updateUser(User, User)}
     */
    @Test
    public void testUpdateUser3() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        login.addUser(new User("john.smith@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        // Act
        boolean actualUpdateUserResult = login.updateUser(oldUser, new User("jane.doe@example.org", "iloveyou"));

        // Assert
        User getResult = login.users.get(0);
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertNull(getResult.getType());
        assertTrue(actualUpdateUserResult);
    }

    /**
     * Method under test: {@link Login#updateUser(User, User)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testUpdateUser4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "car_accessories.User.getPassword()" because "oldUser" is null
        //       at car_accessories.Login.updateUser(Login.java:145)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Act
        login.updateUser(null, new User("jane.doe@example.org", "iloveyou"));
    }

    /**
     * Method under test: {@link Login#updateUser(User, User)}
     */
    @Test
    public void testUpdateUser5() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        // Act and Assert
        assertFalse(login.updateUser(oldUser, new User("iloveyou", "iloveyou")));
    }

    /**
     * Method under test: {@link Login#updateUser(User, User)}
     */
    @Test
    public void testUpdateUser6() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        User oldUser = new User("jane.doe@example.org", "iloveyou");

        // Act and Assert
        assertFalse(login.updateUser(oldUser, new User("", "iloveyou")));
    }

    /**
     * Method under test: {@link Login#updateUser(User, User)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testUpdateUser7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "car_accessories.User.getEmail()" because "newUser" is null
        //       at car_accessories.Login.updateUser(Login.java:149)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Act
        login.updateUser(new User("jane.doe@example.org", "iloveyou"), null);
    }
}
