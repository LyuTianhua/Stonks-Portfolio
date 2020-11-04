package junit;

import csci310.servlets.LoadProfile;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertTrue;

public class LoadGraphTest {

    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void testDoGet() {

        int user_id = 369;
        int company_id = 88;

        Helper.insert_user_id_name_password(user_id, "LoadGraphUser", "LoadGraphPass");
        Helper.insert_company_id_ticker(company_id, "LULU");
        Helper.insert_stock_company_user_shares(company_id, user_id, 10d);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        LoadProfile lp = new LoadProfile();
        lp.doGet(mocReq, mocRes);
        assertTrue((boolean) mocReq.getAttribute("loaded"));

        Helper.delete_from_stock_where_id(user_id);
        Helper.delete_company_where_id(company_id);
        Helper.delete_user_where_id(user_id);
    }

    @Test
    public void testTimestamp() { }

    @Test
    public void testNthDay() { }

    @Test
    public void testGraph() { }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }

}
