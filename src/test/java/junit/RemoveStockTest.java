package junit;

import csci310.servlets.Database;
import csci310.servlets.RemoveStock;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class RemoveStockTest {

    RemoveStock removeStock;
    PreparedStatement ps;
    ResultSet rs;
    Database db;
    Connection con;
    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void TestDoGet() throws SQLException {

        removeStock = new RemoveStock();

        int user_id = 92;
        int company_id = 93;

        Helper.insert_user_id_name_password(user_id, "removeStockDoGet", "password");
        Helper.insert_company_id_ticker(company_id, "FB");
        Helper.insert_stock_company_user_shares(company_id, user_id, 10);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker_id", "1");

        removeStock.doGet(mocReq, mocRes);

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, user_id);
        ps.setInt(2, company_id);
        rs = ps.executeQuery();

        assertTrue(!rs.next());

        db.closeCon();

        Helper.delete_from_stock_user_company(user_id, company_id);
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