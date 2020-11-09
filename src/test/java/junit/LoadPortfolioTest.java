package junit;

import csci310.servlets.AddStock;
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
        String ticker = "LULU";
        double quantity = 10d;

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", ticker);
        mocReq.addParameter("quantity", String.valueOf(quantity));
        mocReq.addParameter("purchased", "2020-08-04");
        mocReq.addParameter("sold", "2020-11-04");
        AddStock addStock = new AddStock();
        addStock.doGet(mocReq, mocRes);

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
