package csci310.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/LoadPortfolio")
public class LoadPortfolio extends HttpServlet {

    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static PrintWriter pw = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        Database db = new Database();
        Connection con = db.getConn();
        try {
            int id = (int) req.getSession().getAttribute("id");

            ps = con.prepareStatement("select company.id as compId, company.ticker, shares, purchased from stock left join company on stock.company_id = company.id where user_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            String checkbox, ticker, shares, remove;

            pw = res.getWriter();
            pw.format("<tr>\n" +
                    "<th>Include</th>\n" +
                    "<th>Stonk</th>\n" +
                    "<th>Shares</th>\n" +
                    "<th>Remove</th>\n" +
                    "</tr>");

            while (rs.next()) {
                ticker = rs.getString("ticker");
                shares = ticker + "Shares";
                remove = ticker + "Remove";
                checkbox = ticker + "portfolio";

                pw.format("<tr>");
                pw.format("<td> <input id=%s type=checkbox onclick=\"modifyGraph('%s', 'portfolio')\" checked> </td>\n", checkbox, ticker);
                pw.format("<td id=%s > <p> %s </p> </td>\n", ticker, ticker);
                pw.format("<td id=%s> %d </td>\n", shares, rs.getInt("shares"));
                pw.format("<td>\n" +
                        "<button id=%s class='btn btn-danger btn-sm' type='button'" +
                        "data-toggle='modal'" +
                        "data-target='#remove-stock-modal'" +
                        "data-ticker='ticker'" +
                        "onclick=\"remove('%s', 'RemoveStock')\">" +
                        "Remove" +
                        "</button>" +
                        "</td>\n", remove, ticker);
                pw.format("</tr>\n");
            }
            pw.flush();
            pw.close();

        } catch (SQLException | IOException ignored) { }
        db.closeCon();
        req.setAttribute("loaded", true);
    }
}
