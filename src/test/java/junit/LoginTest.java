package junit;

import csci310.servlets.Login;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.sql.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest extends Mockito {

    private static MockHttpServletRequest mocReq;
    private static MockHttpServletResponse mocRes;

    @BeforeClass
    public static void setUp() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }

    @Test
    public void testDoPost() {
        mocReq.addParameter("email", "tu1");
        mocReq.addParameter("password", "tu1pass");

        Login login = new Login();
        login.doPost(mocReq, mocRes);

        /*
        *  0 fail 1 works 2 user not found
        * */
        String auth = (String) mocReq.getAttribute("authenticated");
        assertTrue(auth.equalsIgnoreCase("1"));
    }

    @Test
    public void testHashPassword() {

        String unhashed = "unhashed";
        String hashed = "unhashed";
        hashed = Login.hashPassword(hashed);
        assertFalse(unhashed.equalsIgnoreCase(hashed));

    }

    @Test
    public void testValidate() { assertTrue(Login.authenticated("tu1", "tu1pass")); }

}