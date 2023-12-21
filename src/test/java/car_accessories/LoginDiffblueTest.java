package car_accessories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class LoginDiffblueTest {
    /**
     * Method under test: {@link Login#login()}
     */
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

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Login#setLogged(boolean)}
     *   <li>{@link Login#setUser(User)}
     *   <li>{@link Login#setValidEmail(boolean)}
     *   <li>{@link Login#getRoles()}
     *   <li>{@link Login#isLogged()}
     * </ul>
     */
    @Test
    public void testGettersAndSetters() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Act
        login.setLogged(true);
        login.setUser(new User("jane.doe@example.org", "iloveyou"));
        login.setValidEmail(true);
        int actualRoles = login.getRoles();

        // Assert that nothing has changed
        assertEquals(0, actualRoles);
        assertTrue(login.isLogged());
    }

    /**
     * Method under test: {@link Login#emailValidator(String)}
     */
    @Test
    public void testEmailValidator() {
        // Arrange, Act and Assert
        assertTrue((new Login(new User("jane.doe@example.org", "iloveyou"))).emailValidator("jane.doe@example.org"));
        assertFalse((new Login(new User("jane.doe@example.org", "iloveyou"))).emailValidator("foo"));
        assertFalse((new Login(new User("jane.doe@example.org", "iloveyou"))).emailValidator(""));
        assertFalse((new Login(new User("jane.doe@example.org", "iloveyou")))
                .emailValidator("jane.doe@example.orgjane.doe@example.org"));
    }

    /**
     * Method under test: {@link Login#confirmLogin(int)}
     */
    @Test
    public void testConfirmLogin() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Act and Assert

    }

    /**
     * Method under test: {@link Login#confirmLogin(int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testConfirmLogin2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot read field "verificationCode" because "this.m" is null
        //       at car_accessories.Login.confirmLogin(Login.java:90)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        login.setValidEmail(true);

        // Act
        login.confirmLogin(1);
    }

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
     * Method under test: {@link Login#addUser(User)}
     */
    @Test
    public void testAddUser() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Act and Assert
        assertEquals(5, login.users.size());
        assertTrue(login.addUser(new User("jane.doe@example.org", "iloveyou")));
    }

    /**
     * Method under test: {@link Login#addUser(User)}
     */
    @Test
    public void testAddUser2() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Act and Assert
        assertFalse(login.addUser(new User("Email", "iloveyou")));
    }

    /**
     * Method under test: {@link Login#addUser(User)}
     */
    @Test
    public void testAddUser3() {
        // Arrange

    }

    /**
     * Method under test: {@link Login#addUser(User)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testAddUser4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "car_accessories.User.getEmail()" because "u" is null
        //       at car_accessories.Login.addUser(Login.java:133)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new Login(new User("jane.doe@example.org", "iloveyou"))).addUser(null);
    }

    /**
     * Method under test: {@link Login#Login(User)}
     */
    @Test
    public void testNewLogin() {
        // Arrange and Act
        Login actualLogin = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Assert
        List<User> userList = actualLogin.users;
        assertEquals(4, userList.size());
        User getResult = userList.get(0);
        assertEquals("123456", getResult.getPassword());
        User getResult2 = userList.get(1);
        assertEquals("654321", getResult2.getPassword());
        User getResult3 = userList.get(2);
        assertEquals("987654", getResult3.getPassword());
        assertEquals("Admin", getResult.getType());
        User getResult4 = userList.get(3);
        assertEquals("Admin", getResult4.getType());
        assertEquals("Admin", actualLogin.admins);
        assertEquals("Ahmad", getResult.firstName);
        assertEquals("Ahmad", getResult2.firstName);
        assertEquals("Ahmad", getResult3.firstName);
        assertEquals("Ahmad", getResult4.firstName);
        assertEquals("Ali", getResult.lastName);
        assertEquals("Ali", getResult2.lastName);
        assertEquals("Ali", getResult3.lastName);
        assertEquals("Ali", getResult4.lastName);
        assertEquals("Customer", getResult2.getType());
        assertEquals("Installer", getResult3.getType());
        assertEquals("abdallahdaher785@gmail.com", getResult4.getEmail());
        assertEquals("i.a.s.assad33@gmail.com", getResult3.getEmail());
        assertEquals("ibrahim.sadi.asad@gmail.com", getResult.getEmail());
        assertEquals("ibrahimeceasad@gmail.com", getResult2.getEmail());
        assertNull(actualLogin.u.lastName);
        assertFalse(actualLogin.isLogged());
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

    /**
     * Method under test: {@link Login#deleteUser(User)}
     */
    @Test
    public void testDeleteUser() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));

        // Act and Assert
        assertFalse(login.deleteUser(new User("jane.doe@example.org", "iloveyou")));
    }

    /**
     * Method under test: {@link Login#deleteUser(User)}
     */
    @Test
    public void testDeleteUser2() {
        // Arrange
        Login login = new Login(new User("jane.doe@example.org", "iloveyou"));
        login.addUser(new User("jane.doe@example.org", "iloveyou"));

        // Act and Assert
        assertEquals(4, login.users.size());
        assertTrue(login.deleteUser(new User("jane.doe@example.org", "iloveyou")));
    }

    /**
     * Method under test: {@link Login#deleteUser(User)}
     */
    @Test
    public void testDeleteUser3() {
        // Arrange

    }

    /**
     * Method under test: {@link Login#deleteUser(User)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testDeleteUser4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "car_accessories.User.getEmail()" because "u" is null
        //       at car_accessories.Login.deleteUser(Login.java:160)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new Login(new User("jane.doe@example.org", "iloveyou"))).deleteUser(null);
    }
}
