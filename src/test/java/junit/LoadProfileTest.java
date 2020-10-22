package junit;

import csci310.servlets.LoadProfile;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoadProfileTest {

    private static MockHttpServletRequest mocReq;
    private static MockHttpServletResponse mocRes;

    @BeforeClass
    public static void setUp() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }


    @Test
    public void testDoGet() {

        mocReq.getSession(true).setAttribute("id", 1);
        LoadProfile lp = new LoadProfile();
        lp.doGet(mocReq, mocRes);
    }
}
