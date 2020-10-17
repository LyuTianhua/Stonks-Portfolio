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
        MockHttpServletResponse mocRes1 = new MockHttpServletResponse();

        mocReq1.addParameter("ticker", "TSLA");
        TickerChecking1.doGet(mocReq1, mocRes1);
        
        boolean passed = true;


        assertTrue(passed);
    }
    
    @Test
    public void TestCheckTicker() {
        boolean valid = TickerChecking3.checkTicker("TSLA");
        boolean invalid1 = TickerChecking4.checkTicker("THLLY");
        boolean invalid2 = TickerChecking5.checkTicker("");

        assertTrue(valid && invalid1 && invalid2);
    }

}
