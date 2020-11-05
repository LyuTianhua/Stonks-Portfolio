package junit;

import csci310.servlets.AddStock;
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
    AddStock as;
    PreparedStatement ps;
    ResultSet rs;
    Database db;
    Connection con;
    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void TestDoGet() throws SQLException {
    	as = new AddStock();
        removeStock = new RemoveStock();

        int user_id = 92;

        Helper.insert_user_id_name_password(user_id, "removeStockDoGet", "password");
        
        make_new_mock_objects();        
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.setParameter("ticker", "Z");
        mocReq.setParameter("quantity", "10");
        mocReq.setParameter("purchased", "1601535600");
        mocReq.setParameter("sold", "1603177200");

        as.doGet(mocReq, mocRes);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker_id", "1"); // 

        removeStock.doGet(mocReq, mocRes);

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select * from stock where user_id=? and company_id=?");
        ps.setInt(1, user_id);
        ps.setInt(2, 2);
        rs = ps.executeQuery();

        assertTrue(!rs.next());

        db.closeCon();

        Helper.delete_from_stock_user_company(user_id, 2);
        Helper.delete_company_where_id(2);
        Helper.delete_user_where_id(user_id);

    }

    @Test
    public void TestUpdateStock() throws SQLException { }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }
}