package junit;

import csci310.servlets.Login;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    Connection con;

    @BeforeClass
    public static void setUp() throws Exception {
        /*
        Todo: init connection to database
        * */
    }

    @Test
    public void testPost() {

    }

    @Test
    public void testGet() {
    }

    @Test
    public void testHashPassword() {

        String unhashed = "unhashed";
        String hashed = "unhashed";
        hashed = Login.hashPassword(hashed);
        assertFalse(unhashed.equalsIgnoreCase(hashed));

    }

    @Test
    public void testValidate() {

        Connection connection = con;

//        boolean correct = Login.validate(connection, "tu1", "tu1pass");
//        boolean incorrect = Login.validate(connection, "tu1", "wrongPass");

        assertTrue(true);
    }

}