package csci310.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/RemoveStock")
public class RemoveStock {

    public static Connection con = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");

            String email = req.getParameter("email");
            String abbreviation = req.getParameter("abbreviation");
            double shares = Double.parseDouble(req.getParameter("shares"));

            int userId = getUserId(email);
            int companyId = getCompanyId(abbreviation);

            updateStock(userId, companyId, shares);

        } catch (Exception ignored) {}
    }

    public static int getUserId(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT id FROM base_user WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    public static int getCompanyId(String abbreviation) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from company where abbreviation=?");
        ps.setString(1, abbreviation);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    public static void updateStock(int userId, int companyId, double shares) throws SQLException {
        PreparedStatement ps = con.prepareStatement("update stock set shares = shares - ? where user_id=? and company_id=?");
        ps.setDouble(1, shares);
        ps.setInt(2, userId );
        ps.setInt(3, companyId);
        ps.executeUpdate();
    }

}
