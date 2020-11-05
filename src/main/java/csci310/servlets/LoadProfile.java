package csci310.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/LoadProfile")
public class LoadProfile  extends HttpServlet {

    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static PrintWriter pw = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        Database db = new Database();
        Connection con = db.getConn();
        try {
            pw = res.getWriter();
            int id = (int) req.getSession().getAttribute("id");

            ps = con.prepareStatement("select company.id as compId, company.ticker, shares, purchased, stock.id as stockID from stock left join company on stock.company_id = company.id where user_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            String ticker, shares, rm, btn, radio, stockID;


            pw.println("<tr>\n" +
                    "<th>Stonk</th>\n" +
                    "<th>Shares</th>\n" +
                    "<th>Remove</th>\n" +
                    "</tr>");

            while (rs.next()) {
            	stockID = rs.getString("stockID");
                ticker = rs.getString("ticker");
                shares = ticker + "Shares";
                rm = ticker + "Rm";
                btn = ticker + "Btn";
                radio = ticker + "Radio";

                pw.println("<tr>");
                pw.println("<td id=\"" + ticker + "\">" + ticker + "</td>");
                pw.println("<td id=\"" + shares + "\">" + rs.getInt("shares") + "</td>");
                pw.println("<td> " +
                        "<button id=\"" + btn + "\" " + "class=\"btn btn-danger\"" +
                        "type=\"button\" " +
                        "data-toggle=\"modal\"" + 
                        "data-target=\"#remove-stock-modal\"" +
                        "data-ticker=\"" + ticker + "\"" +
                        "onclick=\"remove('" + stockID + "')\">" +
                        "Remove" +
                        "</button>" +
                        "</td>");
                pw.println("</tr>");
            }

            pw.flush();
            pw.close();

        } catch (SQLException | IOException e) { e.printStackTrace();}

        req.setAttribute("loaded", true);
        db.closeCon();
    }

}
