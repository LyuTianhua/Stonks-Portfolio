package csci310.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class Signup extends HttpServlet {

    Connection conn = null;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }



    private static String hashPassword(String input) {
        return "";
    }

    private void insertNewUser(Connection conn, String email, String username, String hashedPass, String favorites) {
    }

    private boolean validEmail(String email) throws SQLException {
        return true;
    }

    public int getUserId(String email) throws SQLException {
        return 0;
    }

}
