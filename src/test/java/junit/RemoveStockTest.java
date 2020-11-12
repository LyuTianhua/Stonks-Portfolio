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

import static org.junit.Assert.assertTrue;

public class RemoveStockTest {

    RemoveStock removeStock;
    PreparedStatement ps;
    ResultSet rs;
    Database db;
    Connection con;
    MockHttpServletRequest mocReq;
    MockHttpServletResponse mocRes;

    @Test
    public void TestDoGet() {

        //using admin user
        int user_id = 1;
        int company_id = 921;
        String  ticker = "F";
        String quantity = "10";
        String purchased = "2020-10-10";
        String sold = "2020-10-20";

        Helper.insert_user_id_name_password(user_id, "RemoveStockUser", "RemoveStockPass");

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
        mocReq.addParameter("ticker", "5");
        RemoveStock rs = new RemoveStock();
        rs.doGet(mocReq, mocRes);
        assertTrue((boolean) mocReq.getAttribute("removed"));
    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }
}