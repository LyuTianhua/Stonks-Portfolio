package junit;

import csci310.servlets.AddHistorical;
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

public class AddHistoricalTest {

    AddHistorical ah;
    AddStock addStock;
    Database db;
    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void testDoGet() {

        ah = new AddHistorical();

        int user_id = 99;
        int company_id = 67;
        int newCompanyId;
        String name = "addStockTestUser";
        String password = "password";
        String ticker = "K";
        String quantity = "10";

        Helper.insert_user_id_name_password(user_id, name, password);
        Helper.insert_company_id_ticker(company_id, ticker);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", ticker);

        ah.doGet(mocReq, mocRes);

        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("select * from company where ticker=?");
            ps.setString(1, ticker);
            rs = ps.executeQuery();
            rs.next();
            newCompanyId = rs.getInt("id");
        } catch (SQLException ignored) {}
        db.closeCon();

        assertTrue((boolean) mocReq.getAttribute("loaded"));

        Helper.delete_company_where_id(company_id);
        Helper.delete_user_where_id(user_id);
    }

    @Test
    public void testGetCompanyId() {

    }

    @Test
    public void insertHistoricalStock() {

    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }

}
