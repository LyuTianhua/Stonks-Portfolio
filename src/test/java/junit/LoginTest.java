package junit;

import csci310.servlets.Login;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest extends Mockito {

    private static MockHttpServletRequest mocReq;
    private static MockHttpServletRequest mocReq2;
    private static MockHttpServletResponse mocRes;
    private static MockHttpServletResponse mocRes2;

    @BeforeClass
    public static void setUp() {
        mocReq = new MockHttpServletRequest();
        mocReq2 = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
        mocRes2 = new MockHttpServletResponse();
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        mocReq.addParameter("email", "tu1@email.com");
        mocReq.addParameter("password", "tu1pass");

        Login login = new Login();
        login.doPost(mocReq, mocRes);
        String auth = (String) mocReq.getAttribute("authenticated");

        mocReq2.addParameter("email", "wrong");
        mocReq2.addParameter("password", "wrong");
        Login loginFail = new Login();
        loginFail.doPost(mocReq2, mocRes2);
        String auth2 = (String) mocReq2.getAttribute("authenticated");

        boolean passed = false;

        if (auth.equals("1") && auth2.equals("0"))
            passed = true;

        /*
        *  0 fail 1 works 2 user not found
        * */

        assertTrue(passed);
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

        boolean valid = Login.authenticated("tu1@email.com", "tu1pass");
        boolean invalid = Login.authenticated("bad connection", "bad connection");

        assertTrue(valid && !invalid);
    }

}