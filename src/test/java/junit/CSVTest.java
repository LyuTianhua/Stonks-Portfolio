package junit;

import csci310.servlets.CSV;
import csci310.servlets.Database;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public void TestDoGet() {

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

        //Test getting malformed ticker
        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("path", "malformedCsvTicker.csv");
        csv.doGet(mocReq, mocRes);
        assertTrue(mocRes.getStatus()  == 404);

        //Test getting malformed date
        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("path", "malformedCsvDate.csv");
        csv.doGet(mocReq, mocRes);
        assertTrue(mocRes.getStatus()  == 405);

        //Test getting malformed quantity
        make_new_mock_objects();
        mocReq.getSession(true).setAttribute("id", user_id);
        mocReq.addParameter("path", "malformedCsvNegativeQuantity.csv");
        csv.doGet(mocReq, mocRes);
        assertTrue(mocRes.getStatus()  == 406);
    }

    public void make_new_mock_objects() {
        mocReq = new MockHttpServletRequest();
        mocRes = new MockHttpServletResponse();
    }
}
