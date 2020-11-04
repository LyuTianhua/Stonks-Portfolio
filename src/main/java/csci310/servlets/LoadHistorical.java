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
        pw = res.getWriter();

        try {
            int id = (int) req.getSession().getAttribute("id");

            ps = con.prepareStatement("select company.ticker as ticker from historicalStock left join company on historicalStock.company_id=company.id where user_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            pw.println("<tr>\n" +
                    "<th>Graph</th>\n" +
                    "<th>Stonk</th>\n" +
                    "<th>Remove</th>\n" +
                    "</tr>");

            String ticker;
            while (rs.next()) {

                ticker = rs.getString("ticker");
                System.out.printf("ticker: %s\n", ticker);

                System.out.println("\n\n\n\n" + ticker + "\n\n\n");

                pw.println("<tr>");
                pw.println("<td> <input id='" + ticker + "Historical" + "' type='checkbox' onchange='graphHistorical(\"" + ticker + "\")'></td>");
                pw.println("<td id=\"" + ticker + "Historical" + "\">" + ticker + "</td>");
                pw.println("<td> " +
                        "<button id= " + "class=\"btn btn-danger\"" +
                        "type=\"button\" " +
                        "data-toggle=\"modal\"" +
                        "data-target=\"#remove-stock-modal\"" +
                        "onclick=\"remove('" + ticker + "', '" + rs.getInt("shares") + "')\">" +
                        "Remove" +
                        "</button>" +
                        "</td>" + "</tr>");
            }


        } catch (SQLException ignored) { }

        req.setAttribute("loaded", true);
        pw.flush();
        pw.close();
        req.setAttribute("loaded", true);
        db.closeCon();
    }


}
