package csci310.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {

    Connection con = null;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /* TODO: handle post request to login */

        /* Connect to database */

        String email = ""; /* get email from post request */
        String password = ""; /* get password from post request */

        /* todo: validate user with database */
        if (validate(con, email, hashPassword(password))) {
            /* set true response attribute */
        }
        else {
            /* set false response attribute */
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }

    //Source for hash password
    //https://veerasundar.com/blog/2010/09/storing-passwords-in-java-web-application/
    public static String hashPassword(String input) {
        return "";
    }

    // leave Connection parameter its for testing purposes
    public static boolean validate(Connection con, String email, String hashPass) {
        return true;
    }

}
