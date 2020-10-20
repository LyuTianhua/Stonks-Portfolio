package junit;

import csci310.servlets.AddStock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;
import java.sql.*;

import static org.junit.Assert.assertEquals;

public class AddStockTest {

    public static AddStock addStock;

    @Before
    public void setUp() throws Exception {
        addStock = new AddStock();
        Connection con = DriverManager.getConnection("jdbc:sqlite:csci310.db");

        PreparedStatement ps = con.prepareStatement("delete from stock where user_id=1 and company_id=1");
        ps.execute();
        con.close();
    }

    @Test
    public void TestDoGet() throws SQLException {
        MockHttpServletRequest mocReq = new MockHttpServletRequest();
        MockHttpServletResponse mocRes = new MockHttpServletResponse();

        HttpSession reqSession = mocReq.getSession(true);
        reqSession.setAttribute("id", 1);
        mocReq.addParameter("ticker", "TSLA");
        mocReq.addParameter("company", "Tesla");
        mocReq.addParameter("quantity", "10");

        addStock.doGet(mocReq, mocRes);

        Connection con = DriverManager.getConnection("jdbc:sqlite:csci310.db");
        PreparedStatement ps = con.prepareStatement("select * from Stock where user_id=1 and company_id=1");
        ResultSet rs = ps.executeQuery();

        assertEquals(10, rs.getDouble("shares"), 0.0);
        con.close();
    }

    @Test
    public void TestGetUserId() throws SQLException {
        assertEquals(AddStock.getUserId("tu1@email.com"), 1);
    }

    @Test
    public void TestGetCompanyId() throws SQLException {
        //testing existing company should get id from db
        assertEquals(AddStock.getCompanyId("TSLA"), 1);

        //testing adding company not in db, adds company returns id
        assertEquals(AddStock.getCompanyId("LULU"), 2);
    }

    @Test
    public void TestAddStockToPortfolio() throws SQLException {
        AddStock.addStockToPortfolio(1, 1, 10, new Date(2010, 10, 10), "");
        Connection con = DriverManager.getConnection("jdbc:sqlite:csci310.db");

        PreparedStatement ps = con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        ResultSet rs = ps.executeQuery();
        rs.next();
        assertEquals(10, rs.getDouble("shares"), 0.0);
        con.close();

        AddStock.addStockToPortfolio(1, 1, 10, new Date(2020, 10, 11), "");
        con = DriverManager.getConnection("jdbc:sqlite:csci310.db");

        ps = con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        rs = ps.executeQuery();
        rs.next();

        assertEquals(20, rs.getDouble("shares"), 0.0);
        con.close();
    }
    
    @Test
    public void TestGetGraphData() throws Exception {
        assert(AddStock.getGraphData("").length() == 0);
    }

}