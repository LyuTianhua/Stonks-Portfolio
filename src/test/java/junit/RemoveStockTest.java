package junit;

import csci310.servlets.Database;
import csci310.servlets.RemoveStock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpSession;
import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RemoveStockTest {

    public static RemoveStock removeStock;
    public static Connection con;

    @Before
    public void setUp() throws Exception {
        removeStock = new RemoveStock();

    }

    @Test
    public void TestDoGet() throws SQLException {
        MockHttpServletRequest mocReq = new MockHttpServletRequest();
        MockHttpServletResponse mocRes = new MockHttpServletResponse();

        Database db = new Database();
        Connection con = db.getConn();

        PreparedStatement ps = con.prepareStatement("delete from stock where user_id=?");
        ps.setInt(1, 1);
        ps.execute();

        ps = con.prepareStatement("insert into stock (user_id, company_id, shares, purchased) values (?, ?, ?, current_date)");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        ps.setDouble(3, 10);
        ps.execute();
        db.closeCon();

        HttpSession session = mocReq.getSession(true);
        assert session != null;
        session.setAttribute("id", 1);

        mocReq.addParameter("ticker", "TSLA");
        mocReq.addParameter("quantity", "5");

        removeStock.doGet(mocReq, mocRes);

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        ResultSet rs = ps.executeQuery();
        rs.next();
        assertEquals(5, rs.getDouble("shares"), 0.0);
        db.closeCon();

        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
        mocReq.addParameter("email", "tu1@email.com");
        mocReq.addParameter("ticker", "TSLA");
        mocReq.addParameter("quantity", "5");

        removeStock.doGet(mocReq, mocRes);

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        rs = ps.executeQuery();
        rs.next();

        assertFalse(rs.next());
        db.closeCon();
    }

    @Test
    public void TestGetCompanyId() throws SQLException {
        assertEquals(RemoveStock.getCompanyId("TSLA"), 1);
    }

    @Test
    public void TestUpdateStock() throws SQLException {

        Database db = new Database();
        Connection con = db.getConn();

        PreparedStatement ps = con.prepareStatement("delete from stock where user_id=?");
        ps.setInt(1, 1);
        ps.execute();

        ps = con.prepareStatement("insert into stock (user_id, company_id, shares, purchased) values (?, ?, ?, current_date)");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        ps.setDouble(3, 10);
        ps.execute();
        db.closeCon();

        RemoveStock.updateStock(1, 1, 5);

        con = db.getConn();
        ps = con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, 1);
        ps.setInt(2, 1);
        ResultSet rs = ps.executeQuery();
        rs.next();
        assertEquals(5, rs.getDouble("shares"), 0.0);
        db.getConn();
    }
}