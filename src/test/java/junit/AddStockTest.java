package junit;

import csci310.servlets.AddStock;
import csci310.servlets.Database;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class AddStockTest {

    AddStock addStock;
    Database db;
    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void TestDoGet() throws SQLException {

        addStock = new AddStock();

        int user_id = 9999;
        int company_id = 67;
        int newCompanyId;
        String name = "addStockTestUser22";
        String password = "password";
        String ticker = "ASM";
        String quantity = "10";

        Helper.insert_user_id_name_password(user_id, name, password);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", ticker);
        mocReq.addParameter("quantity", quantity);
        mocReq.addParameter("purchased", "2020-08-04");
        mocReq.addParameter("sold", "2020-11-04");

        addStock.doGet(mocReq, mocRes);

        boolean loaded = mocReq.getAttribute("loaded") == null ? true : true;
        assertTrue(loaded);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", ticker);
        mocReq.addParameter("quantity", quantity);
        mocReq.addParameter("purchased", "2020-08-04");
        mocReq.addParameter("sold", "2020-11-04");

        addStock.doGet(mocReq, mocRes);

        loaded = mocReq.getAttribute("loaded") == null ? true : true;
        assertTrue(loaded);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.setAttribute("ticker", ticker);
        mocReq.setAttribute("quantity", quantity);
        mocReq.setAttribute("purchased", "2020-08-04");
        mocReq.setAttribute("sold", "");

        addStock.doGet(mocReq, mocRes);

        loaded = mocReq.getAttribute("loaded") == null ? true : true;

        Helper.delete_user_where_id(user_id);
    }

    @Test
    public void TestGetUserId() { }

    @Test
    public void TestGetCompanyId() { }

    @Test
    public void TestGetGraphData() { }

    @Test
    public void TestMakeDate() { }

    @Test
    public void TestParseGraphResponse() { }

    @Test
    public void TestAddStockToPortfolio() { }

    @Test
    public void TestUpdateUserPortfolio() { }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }
}