package junit;

import csci310.servlets.LoadPortfolio;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoadPortfolioTest {

    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void testDoGet() {

        int user_id = 791;
        int company_id = 888;

        Helper.insert_user_id_name_password(user_id, "LoadProfileUser", "LoadProfilePass");
        Helper.insert_company_id_ticker(company_id, "LULU");
        Helper.insert_stock_company_user_shares(company_id, user_id, 10d);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        LoadPortfolio lp = new LoadPortfolio();
        lp.doGet(mocReq, mocRes);

        Helper.delete_from_stock_where_id(user_id);
        Helper.delete_company_where_id(company_id);
        Helper.delete_user_where_id(user_id);
    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }

}
