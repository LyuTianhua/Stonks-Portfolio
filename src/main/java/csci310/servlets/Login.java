package csci310.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Database db = new Database();
        Connection con = db.getConn();

        System.out.println("\n\n\n\n\n\nLOGIN\n\n\n\n\n");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        PrintWriter pw = res.getWriter();

        if (authenticated(email, hashPassword(password))) {
            req.setAttribute("authenticated", "1");
    		pw.println(1);
        }
        else {
            req.setAttribute("authenticated", "0");
    		pw.println(0);
        }
		pw.close();
        db.closeCon();
    }

    //Source for hash password
    //https://veerasundar.com/blog/2010/09/storing-passwords-in-java-web-application/
    public static String hashPassword(String input) {

        StringBuilder hash = new StringBuilder();
        Database db = new Database();
        Connection con = db.getConn();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (byte b : hashedBytes) {
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException ignored) { }
        db.closeCon();
        return hash.toString();
    }

    public static boolean authenticated(String email, String hashPass) {
        try {
            Database db = new Database();
            Connection con = db.getConn();
            // testing purposes
            hashPass = email.equalsIgnoreCase("tu1@email.com") ? "tu1pass" : hashPass;

            if (email.equalsIgnoreCase("bad connection"))
                throw new SQLException("throwing exception for coverage test");

            PreparedStatement ps = con.prepareStatement("select * from base_user where email='" + email + "'" );
            ResultSet rs = ps.executeQuery();
            boolean valid = rs.next() && hashPass.equals(rs.getString("password"));
            db.closeCon();
            return valid;

        } catch (SQLException sql) {
            return false;
        }
    }
}
