package junit;

import csci310.servlets.LoadProfile;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.sql.SQLException;
import static org.junit.Assert.assertTrue;
public class LoadProfileTest {


    @Test
    public void testDoGet() throws SQLException {
        MockHttpServletRequest mocReq = new MockHttpServletRequest();
        MockHttpServletResponse mocRes = new MockHttpServletResponse();

//        Connection con = DriverManager.getConnection("jdbc:sqlite:csci310.db");
//        PreparedStatement ps = con.prepareStatement("insert into Stock (company_id, user_id, shares, purchased) values (1, 1, 1, current_date)");
//        ps.execute();
//        con.close();

        mocReq.getSession(true).setAttribute("id", 1);
        LoadProfile lp = new LoadProfile();
        lp.doGet(mocReq, mocRes);
        assertTrue((Boolean) mocReq.getAttribute("loaded"));
    }
}
