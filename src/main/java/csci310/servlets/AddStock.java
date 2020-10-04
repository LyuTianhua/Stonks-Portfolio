package csci310.servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/AddStock")
public class AddStock  extends HttpServlet {

    public static Connection con = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");

            String email = req.getParameter("email");
            String abbreviation = req.getParameter("abbreviation");
            String company = req.getParameter("company");
            double shares = Double.parseDouble(req.getParameter("shares"));

            int userId = getUserId(email);
            int companyId = getCompanyId(abbreviation, company);

            addStockToPortfolio(userId, companyId, shares);

        } catch (Exception ignored) { }



    }

    public static int getUserId(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT id FROM base_user WHERE email=?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

    public static int getCompanyId(String abbreviation, String name) throws SQLException {

        PreparedStatement ps = con.prepareStatement(
                "with i as (INSERT INTO company (abbreviation, name) VALUES (?, ?) ON CONFLICT (abbreviation) DO NOTHING RETURNING id) select id from i union all select id from company where abbreviation = ? limit 1;");
        ps.setString(1, abbreviation);
        ps.setString(2, name);
        ps.setString(3, abbreviation);

        ResultSet rs = ps.executeQuery();
        rs.next();

        return rs.getInt("id");
    }

    public static void addStockToPortfolio(int userId, int companyId, double shares) throws SQLException {

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

    }

}
