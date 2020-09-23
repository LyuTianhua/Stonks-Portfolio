package csci310.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        /* TODO: handle post request to login */

        System.out.println("\n\n\nhere\n\n\n");

        /* Connect to database */
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        /* todo: validate user with database */
        if (authenticated(email, hashPassword(password))) {
            req.setAttribute("authenticated", "1");
        }
        else {
            req.setAttribute("authenticated", "0");
        }

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
            for (byte b : hashedBytes) {
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }

    public static boolean authenticated(String email, String hashPass) {

        // testing purposes
        hashPass = email.equalsIgnoreCase("tu1")? "tu1pass" : hashPass;

        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");
            PreparedStatement ps = con.prepareStatement("select * from users where email='" + email + "'" );
            ResultSet rs = ps.executeQuery();
            return (rs.next() && hashPass.equals(rs.getString("password")));

        } catch (SQLException sql) {
            return false;
        }
    }

}
