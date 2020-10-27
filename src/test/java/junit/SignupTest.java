package junit;

import csci310.servlets.Signup;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignupTest {

    Signup signup;
    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void testDoPost() throws IOException {

        signup = new Signup();

        Helper.delete_user_where_name("signupTestNewUser");

        make_new_mock_objects();
        add_name_password_confirm("signupTestNewUser", "password", "password");
        signup.doPost(mocReq, mocRes);
        assertTrue((boolean) mocReq.getAttribute("authenticated"));

        Helper.delete_user_where_name("signupTestNewUser");


        Helper.insert_user_name_password("existingUser", "password");

        make_new_mock_objects();
        add_name_password_confirm("existingUser", "password", "password");
        signup.doPost(mocReq, mocRes);
        assertFalse((boolean) mocReq.getAttribute("authenticated"));

        Helper.delete_user_where_name("existingUser");


        make_new_mock_objects();
        add_name_password_confirm("missingConfirm", "password", "");
        signup.doPost(mocReq, mocRes);
        assertFalse((boolean) mocReq.getAttribute("authenticated"));

        make_new_mock_objects();
        add_name_password_confirm("passwordsDontMatch", "dont", "match");
        signup.doPost(mocReq, mocRes);
        assertFalse((Boolean) mocReq.getAttribute("authenticated"));

    }

    @Test
    public void testHashPassword() { }

    @Test
    public void testNewUserInserted() { }

    @Test
    public void testValidEmail() { }

    @Test
    public void getUserId() { }

    public void add_name_password_confirm(String name, String password, String confirm) {
        mocReq.addParameter("email", name);
        mocReq.addParameter("password", password);
        mocReq.addParameter("confirm", confirm);
    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }

}