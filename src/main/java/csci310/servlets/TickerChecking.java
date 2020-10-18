package csci310.servlets;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@WebServlet("/TickerChecking")
public class TickerChecking extends HttpServlet {
	public static PrintWriter pw;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            String ticker = req.getParameter("ticker").toUpperCase();
            
            pw = res.getWriter();
            
            if (checkTicker(ticker)) {     
                req.setAttribute("authenticated", "1");    
            	pw.println(1);
            } else {
                req.setAttribute("authenticated", "0"); 
            	pw.println(0);
            }
            
            pw.close();
        } catch (Exception ignored) {}
    }
        
	public static Boolean checkTicker(String ticker) {
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://yahoo-finance-low-latency.p.rapidapi.com/v6/finance/quote?symbols=" + ticker)
					.header("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
					.header("x-rapidapi-key", "f1e55eb2c9msh2690eb51d13f30ap1d7cdajsn96819c5a1864")
					.asJson();
			if (response.getStatus() == 200) {
				JSONObject obj = new JSONObject(response.getBody().getObject().toString());
				String exchange = obj.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getString("fullExchangeName");
				if (exchange.toLowerCase().contains("nasdaq")) return true;
				else return false;
			}
		} catch (Exception ignored) {}
		return false;
	}
}
