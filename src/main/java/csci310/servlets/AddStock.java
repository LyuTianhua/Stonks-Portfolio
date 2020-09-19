package csci310.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/add_stock")
public class AddStock  extends HttpServlet {

    Connection con = null;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String email = ""; /* get email from request */
        String stock = ""; /* get stock from request */
        double quantity = 0.0; /* get quantity from request */

        int userId = getUserId(email);

        addStockToPortfolio(userId, stock, quantity);

        return;

    }

    private int getUserId(String email) {
        return 0;
    }

    private void addStockToPortfolio(int userId, String stock, double quantity) {
    }

}
