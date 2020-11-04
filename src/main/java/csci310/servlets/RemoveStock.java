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
        System.out.println("\n\n\n\n\n\n\nin remove stock");

        String ticker = req.getParameter("ticker");
        int companyId = getCompanyId(ticker);
        int userId = (int) req.getSession().getAttribute("id");
        System.out.println("ticker: " + ticker);
//            double quantity = Double.parseDouble(req.getParameter("quantity"));
        updateStock(userId, companyId);
    }

    public static int getCompanyId(String ticker)  {
        db = new Database();
        con = db.getConn();
        int id = 0;
        try {
            ps = con.prepareStatement("select * from company where ticker=?");
            ps.setString(1, ticker);
            rs = ps.executeQuery();
            rs.next();
            id = rs.getInt("id");
        } catch (SQLException ignored) {}
        db.closeCon();
        return id;
    }

    public static void updateStock(int userId, int companyId) {

        System.out.printf("user id: %d\ncompany id : %d\n", userId, companyId);

        db = new Database();
        con = db.getConn();

        try {
            ps = con.prepareStatement("delete from Stock where company_id=? and user_id=?");
            ps.setInt(1, userId);
            ps.setInt(2, companyId);
            ps.execute();

            System.out.println("executed update stock query");
        } catch (SQLException e) {e.printStackTrace();}
        db.closeCon();
    }

}
