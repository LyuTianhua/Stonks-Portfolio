package junit;

import csci310.servlets.Login;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.sql.*;

public class LoginTest extends Mockito {

    private static MockHttpServletRequest mocReq;
    private static MockHttpServletRequest mocReq2;
    private static MockHttpServletRequest mocReq3;
    private static MockHttpServletResponse mocRes;
    private static MockHttpServletResponse mocRes2;
    private static MockHttpServletResponse mocRes3;

    @BeforeClass
    public static void setUp() {
        mocReq = new MockHttpServletRequest();
        mocReq2 = new MockHttpServletRequest();
        mocReq3 = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
        mocRes2 = new MockHttpServletResponse();
        mocRes3 = new MockHttpServletResponse();
    }

    @Test
    public void testDoPost() throws IOException {

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

        assertTrue(passed);

        //Test the checkAllAttempts
        mocReq3.addParameter("email", "testuser3@email.com");
        mocReq3.addParameter("password", "wrong");


        loginFail.doPost(mocReq3, mocRes3);
        loginFail.doPost(mocReq3, mocRes3);
        loginFail.doPost(mocReq3, mocRes3);
        loginFail.doPost(mocReq3, mocRes3);
        String auth3 = (String) mocReq3.getAttribute("authenticated");
        boolean pastThreeAttempts = auth3.equals("3");
        assertTrue(pastThreeAttempts);
    }

    @Test
    public void testHashPassword() {

        String unhashed = "unhashed";
        String hashed = "unhashed";
        hashed = Login.hashPassword(hashed);
        assertFalse(unhashed.equalsIgnoreCase(hashed));

    }

    @Test
    public void testAddFootprintRecord() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:csci310.db");

            Login login = new Login();
            int size = 0;
            int user_id = 4;
            login.addFootprintRecord(user_id, con);
            //Now we check if there is exists a record
            PreparedStatement ps = con.prepareStatement("select Count(*) as size from UserLoginRecord where user_id=?");
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                size = rs.getInt("size");
            }
            assertTrue(size == 1);

        } catch(SQLException err) {}
        if(con != null) {
            try {
                con.close();
            } catch(SQLException err) {}
        }
    }

    @Test
    public void testCheckForThreeAttempts() {
        Login login = new Login();
        String user_email = "testuser2@email.com";
        String hashed_pass = login.hashPassword("wrong");
        login.authenticated(user_email, hashed_pass);
        login.authenticated(user_email, hashed_pass);

        boolean attempt1 = login.checkForThreeAttempts(user_email);
        assertTrue(attempt1);

        // On 4th try should faile
        login.authenticated(user_email, hashed_pass);
        boolean attempt2 = login.checkForThreeAttempts(user_email);
        assertFalse(attempt2);
    }

    @Test
    public void testValidate() throws SQLException {
        boolean valid = Login.authenticated("tu1@email.com", "tu1pass");
        boolean invalid = Login.authenticated("bad connection", "bad connection");

        assertTrue(valid && !invalid);
    }

}