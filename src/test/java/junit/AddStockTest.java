package junit;

import csci310.servlets.AddStock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddStockTest {
  
    public static AddStock addStock;
    public static Connection con;

    @Before
    public void setUp() throws Exception {
        addStock = new AddStock();
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");
    }

    @Test
    public void TestDoGet() {
        MockHttpServletRequest mocReq = new MockHttpServletRequest();
        MockHttpServletResponse mocRes = new MockHttpServletResponse();

        mocReq.addParameter("email", "tu1@email.com");
        mocReq.addParameter("stock", "TSLA");
        mocReq.addParameter("amount", "10");

        addStock.doGet(mocReq, mocRes);

        assertTrue((boolean)mocReq.getAttribute("sockAdded"));

    }

    @Test
    public void TestGetUserId() throws SQLException {
        assertEquals(AddStock.getUserId("tu1,email"), 1);
    }

    @Test
    public void TestAddStockToPortfolio() throws SQLException {

        AddStock.addStockToPortfolio(1, 1, 10);

        PreparedStatement ps = con.prepareStatement("select * from stock where user_id=1 and company_id=1");
        ResultSet rs = ps.executeQuery();

        assertEquals(rs.getInt("shares"), 10);
    }

}