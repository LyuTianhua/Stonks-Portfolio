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

        try {
            pw = res.getWriter();
            int id = (int) req.getSession().getAttribute("id");

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");
            ps = con.prepareStatement("select company.id as compId, company.ticker, shares, purchased from stock left join company on stock.company_id = company.id where user_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            res.setContentType("text/html");
            String ticker, shares, rm, btn;


            pw.println("<tr>\n" +
                    "<th>Stonk</th>\n" +
                    "<th>Shares</th>\n" +
                    "<th>Purchased</th>\n" +
                    "<th>Remove</th>\n" +
                    "<th>Confirm</th>\n" +
                    "</tr>");

            while (rs.next()) {
                ticker = rs.getString("ticker");
                shares = ticker + "Shares";
                rm = ticker + "Rm";
                btn = ticker + "Btn";

                pw.println("<tr>");
                pw.println("<th id=\"" + ticker + "\">" + ticker + "</th>");
                pw.println("<th id=\"" + shares + "\">" + rs.getInt("shares") + "</th>");
                pw.println("<th>" + rs.getDate("purchased") + "</th>");
                pw.println("<th> <input id=\"" + rm + "\" type =\"test\" </th>");
                pw.println("<th> " +
                        "<button id=\"" + btn + "\" " +
                        "type=\"button\" " +
                        "onclick=\"remove('" + ticker + "', '#" + rm + "')\" " +
                        "style=\"color: black\">remove</button> " +
                        "</th>");
                pw.println("</tr>");
            }

            pw.flush();
            pw.close();

        } catch (SQLException | IOException ignored) { }
    }

}