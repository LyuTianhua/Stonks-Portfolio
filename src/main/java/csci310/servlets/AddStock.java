package csci310.servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/AddStock")
public class AddStock  extends HttpServlet {

    public static Connection con;
    public static PrintWriter pw;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");

            int userId = (int) req.getSession().getAttribute("id");
            String ticker = req.getParameter("ticker").toUpperCase();
            double quantity = Double.parseDouble(req.getParameter("quantity"));
            String fullDate = req.getParameter("date");

            if (fullDate.length() == 0)
                fullDate = "1970-01-01";

            String[] dateParts = fullDate.split("-", 3);
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            int companyId = getCompanyId(ticker);

            addStockToPortfolio(userId, companyId, quantity, new Date(year, month, day));


            pw = res.getWriter();

            pw.println(1);
            pw.close();

        } catch (Exception ignored) { }



    }

    public static int getCompanyId(String ticker) throws SQLException {

        PreparedStatement ps = con.prepareStatement(
                "with i as (INSERT INTO company (ticker) VALUES (?) ON CONFLICT (ticker) DO NOTHING RETURNING id) select id from i union all select id from company where ticker = ? limit 1;");
        ps.setString(1, ticker);
        ps.setString(2, ticker);

        ResultSet rs = ps.executeQuery();
        rs.next();

        return rs.getInt("id");
    }

    public static void addStockToPortfolio(int userId, int companyId, double shares, Date date) throws SQLException {

        PreparedStatement ps = con.prepareStatement("select * from stock where company_id=? and user_id=?");
        ps.setInt(1, userId);
        ps.setInt(2, companyId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {



            ps = con.prepareStatement("update stock set shares = shares + ?, purchased = ? where user_id = ? and company_id = ?");
            ps.setDouble(1, shares);
            ps.setDate(2, date);
            ps.setInt(3, userId);
            ps.setInt(4, companyId);
            ps.executeUpdate();
        }
        else {
            ps = con.prepareStatement("insert into stock (company_id, user_id, shares, purchased) values (?, ?, ?, ?)");
            ps.setInt(1, companyId);
            ps.setInt(2, userId);
            ps.setDouble(3, shares);
            ps.setDate(4, date);
            ps.execute();
        }

    }

}