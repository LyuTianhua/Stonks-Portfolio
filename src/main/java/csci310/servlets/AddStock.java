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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/AddStock")
public class AddStock extends HttpServlet {

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Database db;
    public static Connection con;
    public static PrintWriter pw;
    public static SimpleDateFormat sdf;


    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {

            // initialize SimpleDateFormat used in makeDate
            sdf = new SimpleDateFormat("yyyy-MM-dd");

            // get parameters from single add stock or get attributes from upload CSV servlet
            int userId = (int) req.getSession().getAttribute("id");
            String ticker = req.getParameter("ticker") == null ? (String) req.getAttribute("ticker") : req.getParameter("ticker").toUpperCase();
            double quantity = req.getParameter("quantity") == null ? Double.parseDouble((String) req.getAttribute("quantity")) : Double.parseDouble(req.getParameter("quantity"));
            String purchased = req.getParameter("purchased") == null ? (String) req.getAttribute("purchased") : req.getParameter("purchased");
            String sold = req.getParameter("sold") == null ? (String) req.getAttribute("sold") : req.getParameter("sold");

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
            long purchasedDate = makeDate(purchased);
            long soldDate = makeDate(sold);

            pw = res.getWriter();

            addStockToPortfolio(userId, companyId, quantity, purchasedDate, soldDate);

            updateUserPortfolio(userId, quantity, purchasedDate, soldDate, timestamp, data);

            pw.println(1);
            pw.close();
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

        return response.getStatus() == 200? (JSONObject) result.get(0) : null;
    }

    public long makeDate(String date) throws ParseException {
        Date newDate = sdf.parse(date);
        return newDate.getTime() / 1000;
    }

    public static String parseGraphResponse(String res) {

        String offset = "\"adjclose\":[";
        String data = res.substring(res.lastIndexOf(offset) + offset.length() , res.lastIndexOf("]}]},"));

        String[] individuals = data.split(",", -1);
        String ret = "";
        for (String s : individuals)
            ret += s.substring(0, s.indexOf(".") + 2) + " ";

        return ret;
    }

    public static void addStockToPortfolio(int userId, int companyId, double shares, long purchased, long sold) {
        db = new Database();
        con = db.getConn();

        try {
            ps = con.prepareStatement("select * from stock where company_id=? and user_id=?");
            ps.setInt(1, userId);
            ps.setInt(2, companyId);

            rs = ps.executeQuery();

            if (rs.next()) {
                ps = con.prepareStatement("update stock set shares = shares + ?, purchased = ?, sold = ? where user_id = ? and company_id = ?");
                ps.setDouble(1, shares);
                ps.setLong(2, purchased);

                // Updated graph data
                ps.setLong(3, sold);

                ps.setInt(4, userId);
                ps.setInt(5, companyId);
                ps.executeUpdate();
            } else {
                ps = con.prepareStatement("insert into stock (company_id, user_id, shares, purchased, sold) values (?, ?, ?, ?, ?)");
                ps.setInt(1, companyId);
                ps.setInt(2, userId);
                ps.setDouble(3, shares);
                ps.setLong(4, purchased);

                // Added graph data
                ps.setLong(5, sold);

                ps.execute();
            }
        } catch (SQLException ignored) {}

        db.closeCon();
    }

    private void updateUserPortfolio(int userId, double quantity, long purchasedDate, long soldDate, String timestamp, String companyValues) {

        String[] splitTimestamps = timestamp.split(" ", -1);
        String[] splitCompanyValues = companyValues.split(" ", -1);

        int N = splitTimestamps.length - 1;

        double[] doubleCompanyValues = new double[N];
        long[] longTimestamps = new long[N];
        double[] data = new double[N];

        for (int i = 0; i < N; i++) {
            doubleCompanyValues[i] = Double.parseDouble(splitCompanyValues[i]);
            longTimestamps[i] = Long.parseLong(splitTimestamps[i]);
        }

//        System.out.println("double values");
//        for (double d : doubleCompanyValues) System.out.print(d + " ");
//
//        System.out.println("\nlong timestamps");
//        for (long l : longTimestamps) System.out.print(l + " ");



        db = new Database();
        con = db.getConn();

        try {

            ps = con.prepareStatement("select * from base_user where id=?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {

                String userData = rs.getString("data");
                if (userData != null) {

                    String[] splitUserData = userData.split(" ", -1);
                    for (int i = 0; i < N; i++)
                        data[i] = Double.parseDouble(splitUserData[i]);

                } else {

                    for (int i = 0; i < N; i++ )
                        data[i] = 0;
                }


                int k = 0;
                while (purchasedDate >= longTimestamps[k])
                    k++;


                while (soldDate >= longTimestamps[k]) {
                    data[k] += doubleCompanyValues[k];
                    k++;
                }

                StringBuilder newUserData = new StringBuilder();
                for (int i = 0; i < N; i++)
                    newUserData.append(data[i]).append(" ");

                ps = con.prepareStatement("update base_user set data=? where id=?");
                ps.setString(1, newUserData.toString());
                ps.setInt(2, userId);
                ps.executeUpdate();
            }


        } catch (SQLException e) {e.printStackTrace();}
        db.closeCon();

    }
}
