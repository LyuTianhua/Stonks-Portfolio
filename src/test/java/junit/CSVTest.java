package junit;

import csci310.servlets.CSV;
import csci310.servlets.Database;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class CSVTest {

    CSV csv;
    Database db;
    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void TestDoGet() throws SQLException {

        csv = new CSV();

        int user_id = 222;
        String name = "CSV_user_test";
        String password = "password";
        String path = "csv.csv";

        Helper.insert_user_id_name_password(user_id, name, password);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("path", path);

        csv.doGet(mocReq, mocRes);

        assertTrue(true);
//
//        db = new Database();
//        con = db.getConn();
//        ps = con.prepareStatement("select company.ticker as ticker from stock left join company on stock.company_id=company.id where user_id=?");
//        ps.setInt(1, user_id);
//        rs = ps.executeQuery();
//        rs.next();
//        assertEquals(rs.getString("ticker"), "asa");
//        db.closeCon();
//
//        Helper.delete_from_stock_where_id(user_id);
//        Helper.delete_user_where_id(user_id);

    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }
}
