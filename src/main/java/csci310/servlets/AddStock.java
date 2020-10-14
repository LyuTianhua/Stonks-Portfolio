package csci310.servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/AddStock")
public class AddStock  extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            System.out.println("\n\n\n\n\nADD STOCK\n\n\n\n");

            String email = "tu1@email.com";        //   req.getParameter("email");
            String ticker = req.getParameter("ticker");
            String company = req.getParameter("company");
            double quantity = Double.parseDouble(req.getParameter("quantity"));

            int userId = getUserId(email);
            int companyId = getCompanyId(ticker, company);

            addStockToPortfolio(userId, companyId, quantity);

            PrintWriter pw = res.getWriter();
            
            pw.println(1);
            
            pw.close();
            
            req.setAttribute("resTicker", ticker);
            req.setAttribute("resQuantity", quantity);
        } catch (Exception ignored) { }



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

    public static int getCompanyId(String abbreviation, String name) throws SQLException {
        Database db = new Database();
        Connection con = db.getConn();
        PreparedStatement ps = con.prepareStatement(
                "with i as (INSERT INTO company (abbreviation, name) VALUES (?, ?) ON CONFLICT (abbreviation) DO NOTHING RETURNING id) select id from i union all select id from company where abbreviation = ? limit 1;");
        ps.setString(1, abbreviation);
        ps.setString(2, name);
        ps.setString(3, abbreviation);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Integer id = rs.getInt("id");
        db.closeCon();
        return id;
    }

    public static void addStockToPortfolio(int userId, int companyId, double shares) throws SQLException {
        Database db = new Database();
        Connection con = db.getConn();
        PreparedStatement ps = con.prepareStatement("select * from stock where company_id=? and user_id=?");
        ps.setInt(1, userId);
        ps.setInt(2, companyId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            ps = con.prepareStatement("update stock set shares = shares + ? where user_id = ? and company_id = ?");
            ps.setDouble(1, shares);
            ps.setInt(2, userId);
            ps.setInt(3, companyId);
            ps.executeUpdate();
        }
        else {
            ps = con.prepareStatement("insert into stock (company_id, user_id, shares) values (?, ?, ?)");
            ps.setInt(1, companyId);
            ps.setInt(2, userId);
            ps.setDouble(3, shares);
            ps.execute();
        }
        db.closeCon();
    }

}
