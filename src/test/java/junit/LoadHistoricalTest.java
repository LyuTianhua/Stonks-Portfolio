package junit;

import csci310.servlets.LoadHistorical;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class LoadHistoricalTest {

    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void testDoGet() throws IOException {

        int user_id = 44;
        int company_id = 671;

        Helper.insert_user_id_name_password(user_id, "LoadHistoricalTest", "LoadHistoricalPass");
        Helper.insert_company_id_ticker(company_id, "ABC");
        Helper.insert_historical_stock_company_user_shares(company_id, user_id);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        LoadHistorical lh = new LoadHistorical();
        lh.doGet(mocReq, mocRes);
        assertTrue((boolean) mocReq.getAttribute("loaded"));

        Helper.delete_company_where_id(company_id);
        Helper.delete_from_historical_stock_user(user_id);
        Helper.delete_user_where_id(user_id);

    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }

}
