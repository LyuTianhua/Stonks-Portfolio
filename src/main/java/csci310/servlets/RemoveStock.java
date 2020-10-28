package csci310.servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/RemoveStock")
public class RemoveStock extends HttpServlet {

    static Database db;
    static Connection con;
    static ResultSet rs;
    static PreparedStatement ps;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        db = new Database();
        con = db.getConn();
        try {
            String ticker = req.getParameter("ticker");
            int companyId = getCompanyId(ticker);
            int userId = (int) req.getSession().getAttribute("id");
            double quantity = Double.parseDouble(req.getParameter("quantity"));
            updateStock(userId, companyId, quantity);
        } catch (Exception ignored) {}
        db.closeCon();
    }

    public static int getCompanyId(String ticker) throws SQLException {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("select * from company where ticker=?");
            ps.setString(1, ticker);
            rs = ps.executeQuery();
            rs.next();
            db.closeCon();
            return rs.getInt("id");
        } catch (SQLException ignored) {}
        db.closeCon();
        return 0;
    }

    public static void updateStock(int userId, int companyId, double shares) throws SQLException {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("update stock set shares = shares - ? where user_id=? and company_id=?");
            ps.setDouble(1, shares);
            ps.setInt(2, userId);
            ps.setInt(3, companyId);
            ps.executeUpdate();

            ps = con.prepareStatement("delete from stock where shares <= 0");
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

}
