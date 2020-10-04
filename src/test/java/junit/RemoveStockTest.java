package junit;

import csci310.servlets.AddStock;
import csci310.servlets.RemoveStock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.*;

public class RemoveStockTest {

    public static RemoveStock removeStock;
    public static Connection con;

    @Before
    public void setUp() throws Exception {
        removeStock = new RemoveStock();
        RemoveStock.con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");
        con = RemoveStock.con;

        PreparedStatement ps = con.prepareStatement("delete from stock where user_id=?");
        ps.setInt(1, 1);
        ps.execute();

        ps = con.prepareStatement("insert into stock (user_id, company_id, shares) values (?, ?, ?)");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        ps.setDouble(3, 10);
        ps.execute();
    }

    @Test
    public void TestDoGet() throws SQLException, IOException {
        MockHttpServletRequest mocReq = new MockHttpServletRequest();
        MockHttpServletResponse mocRes = new MockHttpServletResponse();

        mocReq.addParameter("email", "tu1@email.com");
        mocReq.addParameter("abbreviation", "TSLA");
        mocReq.addParameter("shares", "5");

        removeStock.doGet(mocReq, mocRes);


        PreparedStatement ps = RemoveStock.con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        ResultSet rs = ps.executeQuery();
        rs.next();

        assertEquals(5, rs.getDouble("shares"), 0.0);
    }

    @Test
    public void TestGetUserId() throws SQLException {
        assertEquals(RemoveStock.getUserId("tu1@email.com"), 1);
    }

    @Test
    public void TestGetCompanyId() throws SQLException {
        assertEquals(RemoveStock.getCompanyId("TSLA"), 1);
    }

    @Test
    public void TestUpdateStock() throws SQLException {

        RemoveStock.updateStock(1, 1, 5);

        PreparedStatement ps = RemoveStock.con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        ResultSet rs = ps.executeQuery();
        rs.next();
        assertEquals(5, rs.getDouble("shares"), 0.0);
    }


}