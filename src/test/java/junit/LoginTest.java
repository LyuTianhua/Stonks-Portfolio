package junit;

import csci310.servlets.Login;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.mock.web.MockHttpServletRequest.*;

public class LoginTest {

    private static MockHttpServletRequest mocReq;
    private static MockHttpServletResponse mocRes;


    private static Connection con;

    @BeforeClass
    public static void setUp() throws Exception {
        /*
        Todo: init connection to database
        * */
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CSCI_310_PROJECT?user=root&password=MysqlPass11!!&serverTimezone=PST");
    }

    @Test
    public void testPost() throws ServletException, IOException {

        mocReq.addParameter("username", "tu1");
        mocReq.addParameter("password", "tu1pass");

        Login login = new Login();
        login.doPost(mocReq, mocRes);

        boolean verified = (boolean)mocReq.getAttribute("authenticated");
        assertTrue(verified);
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