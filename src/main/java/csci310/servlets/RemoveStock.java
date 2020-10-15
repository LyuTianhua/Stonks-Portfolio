package csci310.servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/RemoveStock")
public class RemoveStock extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        Database db = new Database();
        Connection con = db.getConn();
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
        Database db = new Database();
        Connection con = db.getConn();
        PreparedStatement ps = con.prepareStatement("select * from company where ticker=?");
        ps.setString(1, ticker);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Integer id = rs.getInt("id");
        db.closeCon();
        return id;
    }

    public static void updateStock(int userId, int companyId, double shares) throws SQLException {
        Database db = new Database();
        Connection con = db.getConn();
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
        db.closeCon();
    }

}
