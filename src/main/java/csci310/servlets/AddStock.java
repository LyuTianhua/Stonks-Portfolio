package csci310.servlets;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
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

@WebServlet("/AddStock")
public class AddStock extends HttpServlet {

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Database db;
    public static Connection con;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            // get parameters from single add stock or get attributes from upload CSV servlet
            int userId = (int) req.getSession().getAttribute("id");
            String ticker = req.getParameter("ticker") == null ? (String) req.getAttribute("ticker") : req.getParameter("ticker").toUpperCase();
            double quantity = req.getParameter("quantity") == null ? Double.parseDouble((String) req.getAttribute("quantity")) : Double.parseDouble(req.getParameter("quantity"));
            String purchased = req.getParameter("purchased") == null ? (String) req.getAttribute("purchased") : req.getParameter("purchased");
            String sold = req.getParameter("sold") == null ? (String) req.getAttribute("sold") : req.getParameter("sold");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (sold.length() < 1) sold = sdf.format(new Date());

            // format data for insertion into db
            JSONObject API_response = getGraphData(ticker);

            // parse the data to get values for stock
            String data = parseGraphResponse(API_response.toString());

            // get an array of the timestamp
            String timestamp = API_response.getJSONArray("timestamp").toString();
            timestamp = timestamp.replace(",", " ");
            timestamp = timestamp.replace("[", "");
            timestamp = timestamp.replace("]", "");

            int companyId = getCompanyId(ticker, data, timestamp);
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


            addStockToPortfolio(userId, companyId, quantity, purchasedDate, soldDate, stockData);

            updateUserPortfolio(userId, purchasedDate, soldDate, timestamp.split(" ", -1), data.split(" ", -1));

            req.setAttribute("loaded", true);
        } catch (Exception ignored) {}
    }

    public static int getCompanyId(String ticker, String data, String timestamp)  {
        db = new Database();
        con = db.getConn();
        int id = 0;
        try {
            //Statement to check if the company exists
            ps = con.prepareStatement("select id from company where ticker=?");
            ps.setString(1, ticker);
            rs = ps.executeQuery();

            //If company is not in database insert it;
            if (!rs.next()) {

                ps = con.prepareStatement("INSERT INTO company (ticker, data, timestamps) VALUES (?, ?, ?)");
                ps.setString(1, ticker);
                ps.setString(2, data);
                ps.setString(3, timestamp);
                ps.execute();
                ps = con.prepareStatement("select id from company where ticker=?");
                ps.setString(1, ticker);
                rs = ps.executeQuery();
                rs.next();
            }

            id = rs.getInt("id");

        } catch (SQLException ignored) { }

        db.closeCon();
        return id;

    }

    public static JSONObject getGraphData(String ticker) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://yahoo-finance-low-latency.p.rapidapi.com/v8/finance/chart/" + ticker + "?lang=en&range=1y&region=US&interval=1d")
                .header("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
                .header("x-rapidapi-key", "f1e55eb2c9msh2690eb51d13f30ap1d7cdajsn96819c5a1864")
                .asJson();

        JSONObject json = response.getBody().getObject();
        JSONObject chart = json.getJSONObject("chart");
        JSONArray result = chart.getJSONArray("result");

        return response.getStatus() == 200 ? (JSONObject) result.get(0) : null;
    }

    public static String parseGraphResponse(String res) {

        String offset = "\"adjclose\":[";
        String data = res.substring(res.lastIndexOf(offset) + offset.length() , res.lastIndexOf("]}]},"));

        String[] individuals = data.split(",", -1);
        StringBuilder ret = new StringBuilder();
        for (String s : individuals)
            ret.append(s, 0, s.indexOf(".") + 2).append(" ");

        ret.deleteCharAt(ret.length()-1);

        return ret.toString();
    }

    public static void addStockToPortfolio(int userId, int companyId, double shares, long purchased, long sold, double[] stockData) {
        db = new Database();
        con = db.getConn();

        try {
            ps = con.prepareStatement("insert into stock (company_id, user_id, shares, purchased, sold, data) values (?, ?, ?, ?, ?, ?)");
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

    private void updateUserPortfolio(int userId, long purchasedDate, long soldDate, String[] strTimestamp, String[] strData) {

        int N = strTimestamp.length - 1;

        String userData = "";
        String[] splitUserData;
        String[] oldData = new String[N];
        Arrays.fill(oldData, "0.0");


        double[] data = new double[N];
        Arrays.fill(data, 0d);

        long ts;
        for (int i = 0; i < N; i++) {
            ts = Long.parseLong(strTimestamp[i]);
            if (purchasedDate < ts && ts <= soldDate)
                data[i] += Double.parseDouble(strData[i]);
        }

        db = new Database();
        con = db.getConn();
        try {

            ps = con.prepareStatement("select * from base_user where id=?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next())
                userData = rs.getString("data");

        } catch (SQLException ignored) {}
        db.closeCon();

        if (userData == null)
            splitUserData = oldData;
        else
            splitUserData = userData.split(", ", -1);


        for (int i = 0; i < N; i++)
            data[i] += Double.parseDouble(splitUserData[i]);;

        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("update base_user set data=? where id=?");
            ps.setString(1, Arrays.toString(data).replace("[", "").replace("]", ""));
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException ignored) {}
        db.closeCon();
    }
}
