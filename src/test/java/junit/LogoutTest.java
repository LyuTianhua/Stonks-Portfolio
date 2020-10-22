package junit;

import csci310.servlets.Logout;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;
import static org.junit.Assert.assertTrue;

public class LogoutTest {

    @Test
    public void TestDoGet() {

        MockHttpServletRequest mocReq = new MockHttpServletRequest();
        MockHttpServletResponse mocRes = new MockHttpServletResponse();

        HttpSession session = mocReq.getSession(true);
        assert session != null;
        session.setAttribute("id", 1);

        Logout logout = new Logout();
        logout.doGet(mocReq, mocRes);

        assertTrue(mocReq.isRequestedSessionIdValid());

    }

}
