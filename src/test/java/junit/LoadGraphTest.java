package junit;

import csci310.servlets.LoadProfile;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoadGraphTest {


    @Test
    public void testDoGet() {

        MockHttpServletRequest mocReq = new MockHttpServletRequest();
        MockHttpServletResponse mocRes = new MockHttpServletResponse();

        mocReq.getSession(true).setAttribute("id", 1);
        LoadProfile lp = new LoadProfile();
        lp.doGet(mocReq, mocRes);
    }

    @Test
    public void testGraph() { }

    @Test
    public void testAddValues() { }

}
