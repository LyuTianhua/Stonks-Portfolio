package junit;

import csci310.servlets.AddStock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.assertEquals;

public class AddStockTest {
  
    public static AddStock addStock;
    public static Connection con;

    @Before
    public void setUp() throws Exception {
        addStock = new AddStock();
        AddStock.con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");
        con = AddStock.con;

        PreparedStatement ps = con.prepareStatement("delete from stock where user_id=1 and company_id=1");
        ps.execute();
    }

    @Test
    public void TestDoGet() throws SQLException {
        MockHttpServletRequest mocReq = new MockHttpServletRequest();
        MockHttpServletResponse mocRes = new MockHttpServletResponse();

        mocReq.addParameter("email", "tu1@email.com");
        mocReq.addParameter("ticker", "TSLA");
        mocReq.addParameter("company", "Tesla");
        mocReq.addParameter("quantity", "10");

        addStock.doGet(mocReq, mocRes);


        PreparedStatement ps = AddStock.con.prepareStatement("select shares from stock where user_id=1 and company_id=1");
        ResultSet rs = ps.executeQuery();
        rs.next();

        assertEquals(10, rs.getDouble("shares"), 0.0);
    }

    @Test
    public void TestGetUserId() throws SQLException {
        assertEquals(AddStock.getUserId("tu1@email.com"), 1);
    }

    @Test
    public void TestGetCompanyId() throws SQLException {
        //testing existing company should get id from db
        assertEquals(AddStock.getCompanyId("TSLA", "Tesla"), 1);

        //testing adding company not in db, adds company returns id
        assertEquals(AddStock.getCompanyId("LULU", "Lululemon"), 3);
    }

    @Test
    public void TestAddStockToPortfolio() throws SQLException {
        AddStock.addStockToPortfolio(1, 1, 10);

        PreparedStatement ps = con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        ResultSet rs = ps.executeQuery();
        rs.next();

        assertEquals(10, rs.getDouble("shares"), 0.0);

        AddStock.addStockToPortfolio(1, 1, 10);

        ps = con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        rs = ps.executeQuery();
        rs.next();

        assertEquals(20, rs.getDouble("shares"), 0.0);

    }

}