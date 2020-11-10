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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
            double quantity = req.getParameter("quantity") == null ? Double.parseDouble((String) req.getAttribute("quantity")) : Double.parseDouble(req.getParameter("quantity"));
            String purchased = req.getParameter("purchased") == null ? (String) req.getAttribute("purchased") : req.getParameter("purchased");
            String sold = req.getParameter("sold") == null ? (String) req.getAttribute("sold") : req.getParameter("sold");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (sold.length() < 1) sold = sdf.format(new Date());
            
            // format data for insertion into db
            JSONObject API_response = AddStock.getGraphData(ticker);
            
            // parse the data to get values for stock
            String data = AddStock.parseGraphResponse(API_response.toString());
            
            // get an array of the timestamp
            String timestamp = API_response.getJSONArray("timestamp").toString();
            timestamp = timestamp.replace(",", " ");
            timestamp = timestamp.replace("[", "");
            timestamp = timestamp.replace("]", "");
            
            int companyId = AddStock.getCompanyId(ticker, data, timestamp);
            long purchasedDate = LoadGraph.timestamp(purchased);
            long soldDate = LoadGraph.timestamp(sold);
            
            String[] splitData = data.split(" ", -1);
            String[] splitTimestamps = timestamp.split(" ", -1);

            double[] stockData = new double[splitData.length];
            double[] values = new double[splitData.length];
            long[] times = new long[splitTimestamps.length];

            for (int i = 0; i < splitData.length; i++) {
                stockData[i] = 0d;
                values[i] = Double.parseDouble(splitData[i]);
                times[i] = Long.parseLong(splitTimestamps[i]);
            }

            for (int i = 0; i < splitData.length; i++)
                if (purchasedDate <= times[i] && times[i] <= soldDate)
                    stockData[i] = values[i];

            insertHistoricalStock(userId, companyId, quantity, purchasedDate, soldDate, stockData);

            req.setAttribute("loaded", true);
        } catch (Exception ignored) {}
    }

    public static void insertHistoricalStock(int userId, int companyId, double shares, long purchased, long sold, double[] stockData) {
        db = new Database();
        con = db.getConn();
        
        System.out.println("in insertHistoricalStock()");
        
        try {
            ps = con.prepareStatement("insert into historicalStock (company_id, user_id, shares, purchased, sold, data) values (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, companyId);
            ps.setInt(2, userId);
            ps.setDouble(3, shares);
            ps.setLong(4, purchased);
            ps.setLong(5, sold);
            ps.setString(6, Arrays.toString(stockData).replace("[", "").replace("]", ""));

            ps.execute();
        } catch (SQLException ignored) {}

        db.closeCon();
    }
}
