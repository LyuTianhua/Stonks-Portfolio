package csci310.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/RemoveStock")
public class RemoveStock {
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        Database db = new Database();
        Connection con = db.getConn();
        try {
            String email = req.getParameter("email");
            String abbreviation = req.getParameter("abbreviation");
            double shares = Double.parseDouble(req.getParameter("shares"));

            int userId = getUserId(email);
            int companyId = getCompanyId(abbreviation);

            updateStock(userId, companyId, shares);

        } catch (Exception ignored) {}
        db.closeCon();
    }

    public static int getUserId(String email) throws SQLException {
        Database db = new Database();
        Connection con = db.getConn();
        PreparedStatement ps = con.prepareStatement("SELECT id FROM base_user WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Integer id = rs.getInt("id");
        db.closeCon();
        return id;
    }

    public static int getCompanyId(String abbreviation) throws SQLException {
        Database db = new Database();
        Connection con = db.getConn();
        PreparedStatement ps = con.prepareStatement("select * from company where abbreviation=?");
        ps.setString(1, abbreviation);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Integer id = rs.getInt("id");
        db.closeCon();
        return id;
    }

    public static void updateStock(int userId, int companyId, double shares) throws SQLException {
        Database db = new Database();
        Connection con = db.getConn();
        PreparedStatement ps = con.prepareStatement("update stock set shares = shares - ? where user_id=? and company_id=?");
        ps.setDouble(1, shares);
        ps.setInt(2, userId );
        ps.setInt(3, companyId);
        ps.executeUpdate();
        db.closeCon();
    }

}
