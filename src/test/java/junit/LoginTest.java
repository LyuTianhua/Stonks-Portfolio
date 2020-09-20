package junit;

import csci310.servlets.Login;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private static MockHttpServletRequest mocReq;
    private static MockHttpServletResponse mocRes;

    @BeforeClass
    public static void setUp() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
  }

    @Test
    public void testPost() {

        mocReq.addParameter("username", "tu1");
        mocReq.addParameter("password", "tu1pass");

        Login login = new Login();
        login.doPost(mocReq, mocRes);

        assertTrue((boolean)mocReq.getAttribute("authenticated"));

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
        assertTrue(Login.authenticated("tu1", "tu1pass"));
    }

}