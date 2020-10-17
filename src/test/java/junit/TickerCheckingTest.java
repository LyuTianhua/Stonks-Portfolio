package junit;

import csci310.servlets.Login;
import csci310.servlets.TickerChecking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TickerCheckingTest {
	public static TickerChecking TickerChecking1;
	public static TickerChecking TickerChecking2;
	public static TickerChecking TickerChecking3;
	public static TickerChecking TickerChecking4;
	public static TickerChecking TickerChecking5;

    @Before
    public void setUp() throws Exception {
    	TickerChecking1 = new TickerChecking();
    	TickerChecking2 = new TickerChecking();
    }
    
    @Test
    public void TestDoGet() {
        MockHttpServletRequest mocReq1 = new MockHttpServletRequest();
        MockHttpServletRequest mocReq2 = new MockHttpServletRequest();
        MockHttpServletResponse mocRes1 = new MockHttpServletResponse();
        MockHttpServletResponse mocRes2 = new MockHttpServletResponse();

        mocReq1.addParameter("ticker", "TSLA");
        TickerChecking1.doGet(mocReq1, mocRes1);
        String auth1 = (String) mocReq1.getAttribute("authenticated");

        mocReq2.addParameter("ticker", "THLLY");
        TickerChecking2.doGet(mocReq2, mocRes2);
        String auth2 = (String) mocReq2.getAttribute("authenticated");
        
        boolean passed = false;

        if (auth1.equals("1") && auth2.equals("0"))
            passed = true;

        assertTrue(passed);
    }
    
    @Test
    public void TestCheckTicker() {
        boolean valid = TickerChecking3.checkTicker("TSLA");
        boolean invalid1 = TickerChecking4.checkTicker("THLLY");
        boolean invalid2 = TickerChecking5.checkTicker("");

        assertTrue(valid && !invalid1 && !invalid2);
    }

}
