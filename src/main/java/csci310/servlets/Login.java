package csci310.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {

    private static Connection con = null;

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        /* TODO: handle post request to login */

        /* Connect to database */
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        /* todo: validate user with database */
        if (authenticated(username, hashPassword(password))) {
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

    public static boolean authenticated(String username, String hashPass) {

        // testing purposes
        hashPass = username.equalsIgnoreCase("tu1")? "tu1pass" : hashPass;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CSCI_310_PROJECT?user=root&password=MysqlPass11!!&serverTimezone=PST");

            PreparedStatement ps = con.prepareStatement("select * from Users where username='" + username + "'" );
            ResultSet rs = ps.executeQuery();

            return (rs.next() && hashPass.equals(rs.getString("password")));

        } catch (SQLException | ClassNotFoundException sql) {
            return false;
        }
    }

}
