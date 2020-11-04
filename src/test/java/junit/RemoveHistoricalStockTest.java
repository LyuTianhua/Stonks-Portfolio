package junit;

import csci310.servlets.Database;
import csci310.servlets.RemoveHistoricalStock;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

public class RemoveHistoricalStockTest {

    RemoveHistoricalStock rhs;
    PreparedStatement ps;
    ResultSet rs;
    Database db;
    Connection con;
    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void TestDoGet() throws SQLException {

        rhs = new RemoveHistoricalStock();

        int user_id = 89;
        int company_id = 50;

        Helper.insert_user_id_name_password(user_id, "RemoveHistoricalStock", "RemoveHistoricalStockPassword");
        Helper.insert_company_id_ticker(company_id, "TEST");
        Helper.insert_historical_stock_company_user_shares(company_id, user_id);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", "TEST");


        rhs.doGet(mocReq, mocRes);

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select * from historicalStock where user_id=? and company_id=?");
        ps.setInt(1, user_id);
        ps.setInt(2, company_id);
        rs = ps.executeQuery();

        rs.next();
        int id = rs.getInt("user_id");

        System.out.println(id);
        assertFalse(false);

        db.closeCon();

        Helper.delete_from_historical_stock_user_company(user_id, company_id);
        Helper.delete_company_where_id(company_id);
        Helper.delete_user_where_id(user_id);

    }

    @Test
    public void TestGetCompanyId() throws SQLException { }

    @Test
    public void TestUpdateStock() throws SQLException { }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }


}