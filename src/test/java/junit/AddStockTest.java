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

import static org.junit.Assert.assertEquals;

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

        int user_id = 99;
        int company_id = 67;
        int newCompanyId;
        String name = "addStockTestUser";
        String password = "password";
        String ticker = "K";
        String quantity = "10";

        Helper.insert_user_id_name_password(user_id, name, password);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", ticker);
        mocReq.addParameter("quantity", quantity);

        addStock.doGet(mocReq, mocRes);

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select * from company where ticker=?");
        ps.setString(1, ticker);
        rs = ps.executeQuery();
        rs.next();
        newCompanyId = rs.getInt("id");
        db.closeCon();

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select * from Stock where user_id=? and company_id=?");
        ps.setInt(1, user_id);
        ps.setInt(2, newCompanyId);
        rs = ps.executeQuery();
        rs.next();
        assertEquals(10d, rs.getDouble("shares"), 0.0);
        db.closeCon();

        Helper.delete_from_stock_user_company(user_id, company_id);
        Helper.delete_company_where_ticker(ticker);
        Helper.delete_user_where_id(user_id);

        Helper.insert_user_id_name_password(387, "removeTestUser", password);
        Helper.insert_company_id_ticker(company_id, ticker);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", ticker);
        mocReq.addParameter("quantity", quantity);

        addStock.doGet(mocReq, mocRes);

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select * from Stock where user_id=? and company_id=?");
        ps.setInt(1, user_id);
        ps.setInt(2, company_id);
        rs = ps.executeQuery();
        rs.next();
        assertEquals(10d, rs.getDouble("shares"), 0.0);
        db.closeCon();

        Helper.delete_from_stock_user_company(387, company_id);
        Helper.delete_company_where_id(company_id);
        Helper.delete_user_where_id(387);
    }

    @Test
    public void TestGetUserId() { }

    @Test
    public void TestGetCompanyId() { }

    @Test
    public void TestAddStockToPortfolio() { }

    @Test
    public void TestGetGraphData() { }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }
}