package junit;

import csci310.servlets.*;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;


public class ModifyGraphTest {

    public static MockHttpServletRequest mocReq;
    public static MockHttpServletResponse mocRes;
    @Test
    public void testDoGet() throws IOException {

        String email = "ModifyGraphTestUser";
        String password = "ModifyGraphTestPassword";
        int user_id = 0;
        int company_id = 1234;
        String ticker = "GE";
        String quantity = "10";
        String purchased = "2020-10-10";
        String sold = "2020-10-20";

        make_new_mock_objects();
        mocReq.addParameter("email", email);
        mocReq.addParameter("password", password);
        mocReq.addParameter("confirm", password);
        Signup signup = new Signup();
        signup.doPost(mocReq, mocRes);

        Database db = new Database();
        Connection con = db.getConn();
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.prepareStatement("select * from base_user where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            user_id = rs.getInt("id");
        } catch (SQLException ignored) {}
        db.closeCon();


        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", ticker);
        mocReq.addParameter("quantity", quantity);
        mocReq.addParameter("purchased", purchased);
        mocReq.addParameter("sold", sold);
        AddStock as = new AddStock();
        as.doGet(mocReq, mocRes);

        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("ticker", ticker);
        ModifyGraph mg = new ModifyGraph();
        mg.doGet(mocReq, mocRes);

        assertTrue((boolean) mocReq.getAttribute("modified"));

        Helper.delete_user_where_id(user_id);
        Helper.delete_company_where_ticker(ticker);
        Helper.delete_from_historical_stock_user(user_id);
    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }

}
