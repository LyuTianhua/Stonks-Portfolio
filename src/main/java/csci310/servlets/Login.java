package csci310.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {

    Connection con = null;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }

    // leave Connection parameter its for testing purposes
    public static boolean validate(Connection connection, String username, String hashPass) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Users WHERE username='" + username + "'");
            ResultSet rs = ps.executeQuery();
            return (rs.next() && hashPass.equals(rs.getString("password")));
        } catch (SQLException sql) {
            return false;
        }
    }

}
