package csci310.servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/RemoveStock")
public class RemoveStock extends HttpServlet {

    public static Connection con = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");

            String ticker = req.getParameter("ticker");
            int companyId = getCompanyId(ticker);
            int userId = (int) req.getSession().getAttribute("id");
            double quantity = Double.parseDouble(req.getParameter("quantity"));

            updateStock(userId, companyId, quantity);

        } catch (Exception ignored) {}
    }

    public static int getCompanyId(String ticker) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from company where ticker=?");
        ps.setString(1, ticker);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    public static void updateStock(int userId, int companyId, double shares) throws SQLException {

        PreparedStatement ps = con.prepareStatement("select shares from stock where user_id=? and company_id=?");
        ps.setInt(1, userId);
        ps.setInt(2, companyId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ps = con.prepareStatement("update stock set shares = shares - ? where user_id=? and company_id=?");
            ps.setDouble(1, shares);
            ps.setInt(2, userId );
            ps.setInt(3, companyId);
            ps.executeUpdate();
        }
        ps = con.prepareStatement("delete from stock where shares <= 0");
        ps.execute();

    }

}