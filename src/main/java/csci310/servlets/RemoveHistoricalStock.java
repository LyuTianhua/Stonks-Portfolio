package csci310.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/RemoveHistoricalStock")
public class RemoveHistoricalStock extends HttpServlet {

    static Database db;
    static Connection con;
    static ResultSet rs;
    static PreparedStatement ps;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        String ticker = req.getParameter("ticker");
        int user_id = (int) req.getSession().getAttribute("id");

        db = new Database();
        con = db.getConn();
        int company_id = 0;
        try {
            ps = con.prepareStatement("select * from company where ticker=?");
            ps.setString(1, ticker);
            rs = ps.executeQuery();
            rs.next();
            company_id = rs.getInt("id");

            ps = con.prepareStatement("delete from historicalStock where company_id=? and user_id=?");
            ps.setInt(1, company_id);
            ps.setInt(2, user_id);
            ps.execute();

        } catch (SQLException ignored) {}
        db.closeCon();
    }

}
