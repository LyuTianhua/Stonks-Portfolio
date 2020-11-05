package junit;

import csci310.servlets.AddStock;
import csci310.servlets.LoadGraph;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertTrue;

public class LoadGraphTest {


    @Test
    public void testDoGet() {
        MockHttpServletRequest mocReq = new MockHttpServletRequest();
        MockHttpServletResponse mocRes = new MockHttpServletResponse();
        int user_id = 369;
        int company_id = 88;

        Helper.insert_user_id_name_password(user_id, "LoadGraphUser", "LoadGraphPass");

        AddStock as = new AddStock();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", "FB");
        mocReq.addParameter("quantity", "10");
        mocReq.addParameter("purchased", "2020-10-10");
        mocReq.addParameter("sold", "2020-10-20");
        as.doGet(mocReq, mocRes);


        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("fromGraph", "2020-08-04");
        mocReq.addParameter("toGraph", "2020-11-04");

        LoadGraph lp = new LoadGraph();
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

//    public void make_new_mock_objects() {
//        mocReq = new MockHttpServletRequest();
//        mocRes = new MockHttpServletResponse();
//    }

}
