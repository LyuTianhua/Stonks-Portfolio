package csci310.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/LoadHistorical")
public class LoadHistorical extends HttpServlet {

    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static PrintWriter pw = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Database db = new Database();
        Connection con = db.getConn();

        try {
            int id = (int) req.getSession().getAttribute("id");

            ps = con.prepareStatement("select company.ticker as ticker, shares, purchased from historicalStock left join company on historicalStock.company_id=company.id where user_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            String ticker, checkbox, shares, remove;

            pw = res.getWriter();
            pw.println("<tr>\n" +
                    "<th> <input id='checkAllHistorical' type=checkbox onclick=\"checkAllHistorical()\"> Graph</th>\n" +
                    "<th>Stonk</th>\n" +
                    "<th>Shares</th>\n" +
                    "<th>Remove</th>\n" +
                    "</tr>");

            while (rs.next()) {
                ticker = rs.getString("ticker");
                shares = ticker + "Shares";
                checkbox = ticker + "Historical";
                remove = ticker + "RemoveHistorical";

                pw.format("<tr>");
                pw.format("<td> <input id=%s type=checkbox onclick=\"modifyGraph('%s', 'Historical')\"> </td>\n", checkbox, ticker);
                pw.format("<td id=%s > %s </td>\n", ticker + "View", ticker);
                pw.format("<td id=%s> %d </td>\n", shares, rs.getInt("shares"));
                pw.format("<td>\n" +
                        "<button id=%s class='btn btn-danger btn-sm' type='button'" +
                        "data-toggle='modal'" +
                        "data-target='#remove-stock-modal'" +
                        "data-ticker='ticker'" +
                        "onclick=\"remove('%s', 'RemoveHistoricalStock')\">" +
                        "Remove" +
                        "</button>" +
                        "</td>\n", remove, ticker);
                pw.format("</tr>\n");
            }
            pw.flush();
            pw.close();

        } catch (SQLException ignored) { }
        db.closeCon();
        req.setAttribute("loaded", true);
    }
}
