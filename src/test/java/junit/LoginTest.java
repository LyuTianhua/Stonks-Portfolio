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

        Helper.insert_user_name_password(name, password);

        make_new_mock_objects();
        mocReq.addParameter("email", name);
        mocReq.addParameter("password", password);

        login.doPost(mocReq, mocRes);

        assertTrue((boolean)mocReq.getAttribute("authenticated"));

        Helper.delete_user_where_name(name);


        make_new_mock_objects();
        mocReq.addParameter("email", "wrong");
        mocReq.addParameter("password", "wrong");
        login.doPost(mocReq, mocRes);
        assertFalse((boolean) mocReq.getAttribute("authenticated"));


        //Test the checkAllAttempts

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
    }

    @Test
    public void testAddFootprintRecord() { }

    @Test
    public void testCheckForThreeAttempts() { }

    @Test
    public void testGetUserId() { }

    @Test
    public void testHashPassword() { }

    @Test
    public void testAuthenticate() { }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }
}