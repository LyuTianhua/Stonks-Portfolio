package junit;

import csci310.servlets.Login;
import csci310.servlets.Signup;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.*;

public class SignupTest {

//    @Before
//    public void setUp() throws SQLException {
//        Connection con = DriverManager.getConnection("jdbc:sqlite:csci310.db");
//        PreparedStatement ps = con.prepareStatement("DELETE FROM base_user WHERE email <> 'tu1@email.com'" );
//        ps.execute();
//        con.close();
//    }


    @Test
    public void testDoPost() throws ServletException, IOException {

        MockHttpServletResponse mocRes = new MockHttpServletResponse();
        Signup signup = new Signup();

        MockHttpServletRequest mocReq = newMockRequest("tu2@email.com", "tu2pass", "tu2pass");
        signup.doPost(mocReq, mocRes);
        boolean passed = (Boolean) mocReq.getAttribute("authenticated");
        assertTrue(passed);

        mocReq = newMockRequest("tu1@email.com", "tu1pass", "tu1pass");
        signup.doPost(mocReq, mocRes);
        boolean failEmailExists = (Boolean) mocReq.getAttribute("authenticated");
        assertFalse(failEmailExists);

        mocReq = newMockRequest("tu2@email.com", "tu2pass", "");
        signup.doPost(mocReq, mocRes);
        boolean failMissingParam = (Boolean) mocReq.getAttribute("authenticated");
        assertFalse(failMissingParam);

        mocReq = newMockRequest("tu2@email.com", "password1", "password2");
        signup.doPost(mocReq, mocRes);
        boolean failPasswordsDontMatch = (Boolean) mocReq.getAttribute("authenticated");
        assertFalse(failPasswordsDontMatch);

    }

    @Test
    public void testHashPassword() {
        String unhashed = "unhashed";
        String hashed = unhashed;
        hashed = Login.hashPassword(hashed);
        assertFalse(unhashed.equalsIgnoreCase(hashed));
    }

    @Test
    public void testNewUserInserted() throws SQLException {
        assertTrue(Signup.newUserInserted("insertUserTest", "insertUserPass"));
    }

    @Test
    public void testValidEmail() throws SQLException {
        assertTrue(Signup.validEmail("validEmailTest"));
    }

    @Test
    public void getUserId() throws SQLException {
        assertEquals(Signup.getUserId("tu1@email.com"), 1);
    }

    public MockHttpServletRequest newMockRequest(String email, String password, String confirm) {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addParameter("email", email);
        mockRequest.addParameter("password", password);
        mockRequest.addParameter("confirm", confirm);
        return mockRequest;
    }
}