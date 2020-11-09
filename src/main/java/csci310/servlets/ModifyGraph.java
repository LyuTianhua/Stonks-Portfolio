
package csci310.servlets;

        import org.json.JSONArray;

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


@WebServlet("/ModifyGraph")
public class ModifyGraph extends HttpServlet {

    public static Database db;
    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static PrintWriter pw = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        db = new Database();
        con = db.getConn();
        try {

            int id = (int) req.getSession().getAttribute("id");
            String ticker = req.getParameter("ticker");

            int company_id = 0;

            ps = con.prepareStatement("select id from Company where ticker=?");
            ps.setString(1, ticker);
            rs = ps.executeQuery();

            company_id = rs.getInt("id");

            ps = con.prepareStatement("select data from Stock where company_id=? and user_id=?");
            ps.setInt(1, company_id);
            ps.setInt(2, id);
            rs = ps.executeQuery();

            String strPortfolio = rs.getString("data");
            String[] splitPortfolio = strPortfolio.split(", ", -1);

            double[] data = new double[splitPortfolio.length];
            for (int i = 0; i < splitPortfolio.length; i++)
                data[i] = Double.parseDouble(splitPortfolio[i]);


            pw = res.getWriter();
            pw.println(new JSONArray(data));
            pw.flush();
            pw.close();

        } catch (SQLException | IOException ignored) { }
        db .closeCon();
        req.setAttribute("modified", true);
    }
}

