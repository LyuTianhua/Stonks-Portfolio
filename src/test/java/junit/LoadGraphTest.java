package junit;

import csci310.servlets.LoadProfile;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoadGraphTest {

    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void testDoGet() {

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", 1);
        LoadProfile lp = new LoadProfile();
        lp.doGet(mocReq, mocRes);
    }

    @Test
    public void testGraph() { }

    @Test
    public void testAddValues() { }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }

}
