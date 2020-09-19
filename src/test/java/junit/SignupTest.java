package junit;

import org.junit.BeforeClass;
import org.junit.Test;
import csci310.servlets.Login;

import static org.junit.Assert.assertFalse;

public class SignupTest {

    @BeforeClass
    public static void setUp() throws Exception {
    }

    @Test
    public void testDoPost() {
    }

    @Test
    public void testDoGet() {
    }

    @Test
    public void testHashPassword() {

        String unhashed = "unhashed";
        String hashed = unhashed;
        hashed = Login.hashPassword(hashed);
        assertFalse(unhashed.equalsIgnoreCase(hashed));
    }

    @Test
    public void testInsertNewUser() {

    }

    @Test
    public void testValidEmail() {

    }

    @Test
    public void getUserId() {
    }
}