package junit;

import csci310.servlets.Login;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest extends Mockito {

    Login login;
    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void testDoPost() throws IOException {

        login = new Login();

        int id = 888;
        String name = "loginDoPostTestUser";
        String password = "force_allow";

        Helper.insert_user_id_name_password(id, name, password);

        make_new_mock_objects();
        mocReq.addParameter("email", name);
        mocReq.addParameter("password", password);

        login.doPost(mocReq, mocRes);
        assertTrue(mocReq.getAttribute("authenticated").equals("1"));

        Helper.delete_user_where_name(name);


        make_new_mock_objects();
        mocReq.addParameter("email", "wrong");
        mocReq.addParameter("password", "wrong");
        login.doPost(mocReq, mocRes);
        assertFalse(mocReq.getAttribute("authenticated").equals("1"));


        //Test the checkAllAttempts

        Helper.insert_user_name_password("testuser3@email.com", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8");

        make_new_mock_objects();
        mocReq.addParameter("email", "testuser3@email.com");
        mocReq.addParameter("password", "wrong");

        login.doPost(mocReq, mocRes);
        login.doPost(mocReq, mocRes);
        login.doPost(mocReq, mocRes);
        login.doPost(mocReq, mocRes);
        String auth3 = (String) mocReq.getAttribute("authenticated");
        boolean pastThreeAttempts = auth3.equals("3");
        assertTrue(pastThreeAttempts);

        Helper.delete_user_where_name("testuser3@email.com");
    }

    @Test
    public void testHashPassword() { }

    @Test
    public void testValidate() { }

    @Test
    public void testAddFootprintRecord() {
//        Connection con = null;
//        try {
//            con = DriverManager.getConnection("jdbc:sqlite:csci310.db");
//
//            Login login = new Login();
//            int size = 0;
//            int user_id = 4;
//            Login.addFootprintRecord(user_id, con);
//            //Now we check if there is exists a record
//            PreparedStatement ps = con.prepareStatement("select Count(*) as size from UserLoginRecord where user_id=?");
//            ps.setInt(1, user_id);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                size = rs.getInt("size");
//            }
//            assertTrue(size == 1);
//
//        } catch(SQLException err) {}
//        if(con != null) {
//            try {
//                con.close();
//            } catch(SQLException err) {}
//        }
    }

    @Test
    public void testCheckForThreeAttempts() {
//        Login login = new Login();
//        String user_email = "testuser2@email.com";
//        String hashed_pass = Login.hashPassword("wrong");
//        Login.authenticated(user_email, hashed_pass);
//        Login.authenticated(user_email, hashed_pass);
//
//        boolean attempt1 = Login.checkForThreeAttempts(user_email);
//        assertTrue(attempt1);
//
//        // On 4th try should faile
//        Login.authenticated(user_email, hashed_pass);
//        boolean attempt2 = Login.checkForThreeAttempts(user_email);
//        assertFalse(attempt2);
    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }
}