package csci310.servlets;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/AddHistorical")
public class AddHistorical extends HttpServlet {

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Database db;
    public static Connection con;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            int userId = (int) req.getSession().getAttribute("id");
            String ticker = req.getParameter("ticker");

            // format data for insertion into db
            JSONObject API_response = AddStock.getGraphData(ticker);

            // parse the data to get values for stock
            String data = AddStock.parseGraphResponse(API_response.toString());

            // get an array of the timestamp
            String timestamp = API_response.getJSONArray("timestamp").toString();
            timestamp = timestamp.replace(",", " ");
            timestamp = timestamp.replace("[", "");
            timestamp = timestamp.replace("]", "");


            System.out.println("timestamp: " + timestamp);
            int companyId = AddStock.getCompanyId(ticker, data, timestamp);

            insertHistoricalStock(userId, companyId);

            req.setAttribute("loaded", true);
        } catch (Exception ignored) {}
    }

    public static void insertHistoricalStock(int userId, int companyId) {
        db = new Database();
        con = db.getConn();

        try {
            ps = con.prepareStatement("insert into historicalStock (company_id, user_id) values (?, ?)");
            ps.setInt(1, companyId);
            ps.setInt(2, userId);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }
}
