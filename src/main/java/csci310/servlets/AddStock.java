package csci310.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/add_stock")
public class AddStock  extends HttpServlet {

    public static Connection con = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");

            String email = ""; /* get email from request */
            String stock = ""; /* get stock from request */
            double quantity = 0.0; /* get quantity from request */

            int userId = getUserId(email);

            addStockToPortfolio(userId, stock, quantity);

            return;


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public static int getUserId(String email) {
        return 0;
    }

    public static void addStockToPortfolio(int userId, int companyId, double quantity) {
    }

}
