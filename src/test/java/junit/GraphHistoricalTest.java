package junit;

import csci310.servlets.AddHistorical;
import csci310.servlets.GraphHistorical;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertTrue;

public class GraphHistoricalTest {

    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void testDoGet() {
        int user_id = 3689;
        int company_id = 88;

        Helper.insert_user_id_name_password(user_id, "GraphHistoricalUser369", "GraphHistoricalUser369");
//        Helper.insert_company_id_ticker(company_id, "LULU");
//        Helper.insert_stock_company_user_shares(company_id, user_id, 10d);

        AddHistorical ah = new AddHistorical();

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.setParameter("ticker", "LULU");
        ah.doGet(mocReq, mocRes);


        make_new_mock_objects();
        mocReq.setParameter("ticker", "LULU");
        mocReq.setParameter("checked", "true");
        mocReq.setParameter("fromGraph", "2020-10-10");
        mocReq.setParameter("toGraph", "2020-10-20");
        GraphHistorical gh =  new GraphHistorical();
        gh.doGet(mocReq, mocRes);
        assertTrue((boolean) mocReq.getAttribute("loaded"));

        Helper.delete_from_stock_where_id(user_id);
        Helper.delete_company_where_ticker("LULU");
        Helper.delete_user_where_id(user_id);

        user_id++;
        company_id++;
        Helper.insert_user_id_name_password(user_id, "LoadGraphUser2", "LoadGraphPass2");
        Helper.insert_company_id_ticker(company_id, "K");
        Helper.insert_stock_company_user_shares(company_id, user_id, 10d);

        make_new_mock_objects();
        mocReq.setParameter("ticker", "LULU");
        mocReq.setParameter("checked", "false");
        mocReq.setParameter("fromGraph", "2020-10-10");
        mocReq.setParameter("toGraph", "2020-10-20");
        gh =  new GraphHistorical();
        gh.doGet(mocReq, mocRes);
        assertTrue((boolean) mocReq.getAttribute("loaded"));

        Helper.delete_from_stock_where_id(user_id);
        Helper.delete_company_where_ticker("LULU");
        Helper.delete_user_where_id(user_id);

    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }
}
