package junit;

import csci310.servlets.Login;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private static Connection con;

    @BeforeClass
    public static void setUp() throws Exception {
        /*
        Todo: init connection to database
        * */
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CSCI_310_PROJECT?user=root&password=MysqlPass11!!&serverTimezone=PST");
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

        boolean correct = Login.validate(con, "tu1", "tu1pass");
        boolean incorrect = Login.validate(con, "tu1", "wrongPass");

        assertTrue(correct && !incorrect);
    }

}