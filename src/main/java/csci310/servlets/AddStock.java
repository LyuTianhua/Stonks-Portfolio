package csci310.servlets;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/AddStock")
public class AddStock extends HttpServlet {

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Database db;
    public static Connection con;
    public static PrintWriter pw;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            int userId = (int) req.getSession().getAttribute("id");
            String ticker = req.getParameter("ticker") == null ? (String) req.getAttribute("ticker") : req.getParameter("ticker").toUpperCase();
            double quantity = req.getParameter("quantity") == null ? Double.parseDouble((String) req.getAttribute("quantity")) : Double.parseDouble(req.getParameter("quantity"));
            String purchased = req.getParameter("purchased") == null ? (String) req.getAttribute("purchased") : req.getParameter("purchased");
            String sold = req.getParameter("sold") == null ? (String) req.getAttribute("sold") : req.getParameter("sold");

            String data = getGraphData(ticker);

            int companyId = getCompanyId(ticker, data);

            pw = res.getWriter();

            addStockToPortfolio(userId, companyId, quantity, makeDate(purchased), makeDate(sold));

            pw.println(1);
            pw.close();
            db.closeCon();
        } catch (Exception ignored) {}
    }

    public static int getCompanyId(String ticker, String data)  {
        db = new Database();
        con = db.getConn();
        int id = 0;
        try {
            //Statement to check if the company exists
            ps = con.prepareStatement("select id from company where ticker=?");
            ps.setString(1, ticker);
            rs = ps.executeQuery();

            //If does not exists, Get company id
            if(rs.next()) {
                id = rs.getInt("id");
            } else {
                ps = con.prepareStatement("INSERT INTO company (ticker, data) VALUES (?, ?)");
                ps.setString(1, ticker);
                ps.setString(2, data);
                ps.execute();
                ps = con.prepareStatement("select id from company where ticker=?");
                ps.setString(1, ticker);
                rs = ps.executeQuery();
                rs.next();
                id = rs.getInt("id");
            }
        } catch (SQLException ignored) { }
        db.closeCon();
        return id;

    }

    public static void addStockToPortfolio(int userId, int companyId, double shares, Date purchased, Date sold) {
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
                ps.setDate(2, purchased);

                // Updated graph data
                ps.setDate(3, sold);

                ps.setInt(4, userId);
                ps.setInt(5, companyId);
                ps.executeUpdate();
            } else {
                ps = con.prepareStatement("insert into stock (company_id, user_id, shares, purchased, sold) values (?, ?, ?, ?, ?)");
                ps.setInt(1, companyId);
                ps.setInt(2, userId);
                ps.setDouble(3, shares);
                ps.setDate(4, purchased);

                // Added graph data
                ps.setDate(5, sold);

                ps.execute();
            }
        } catch (SQLException ignored) {}

        db.closeCon();
    }

    public static String getGraphData(String ticker) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://yahoo-finance-low-latency.p.rapidapi.com/v8/finance/chart/" + ticker + "?lang=en&range=1y&region=US&interval=1d")
                .header("x-rapidapi-host", "yahoo-finance-low-latency.p.rapidapi.com")
                .header("x-rapidapi-key", "f1e55eb2c9msh2690eb51d13f30ap1d7cdajsn96819c5a1864")
                .asJson();
        return response.getStatus() == 200? parseGraphResponse(response.getBody().getObject().toString()) : "";
    }

    public static Date makeDate(String d) {
        if (d == null || d.equals(""))
            d = new java.sql.Date(System.currentTimeMillis()).toString();
        String[] dateParts = d.split("-", 3);
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        return new Date(year, month, day);
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
}
