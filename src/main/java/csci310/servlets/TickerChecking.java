package csci310.servlets;
import java.io.PrintWriter;

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

    }
        
	public static Boolean checkTicker(String ticker) {
		return true;
	}
}
