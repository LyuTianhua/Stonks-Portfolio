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
 * Servlet implementation class Signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {

    public static Connection con;

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        PrintWriter pw = res.getWriter();
        try {

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/cs310", "cs310user", "cs310password");

            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String confirm = req.getParameter("confirm");



            if (email.isEmpty() | password.isEmpty() | confirm.isEmpty() | !password.equalsIgnoreCase(confirm)) {
                req.setAttribute("authenticated", false);
                pw.write("0");
                pw.flush();
                pw.close();
                return;
            }

            if (validEmail(email)) {
                newUserInserted(email, hashPassword(password));
                req.setAttribute("authenticated", true);
                pw.write("1");
                pw.flush();
                pw.close();
                return;
            }
        } catch (SQLException ignored) { }

        req.setAttribute("authenticated", false);
        pw.println(0);
        pw.close();
    }

    private static String hashPassword(String input) {
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
        } catch (NoSuchAlgorithmException ignored) { }

        return hash.toString();
    }

    public static boolean newUserInserted(String email, String hashedPass) throws SQLException {
        PreparedStatement ps = con.prepareStatement("insert into base_user (email, password) values (?, ?)" );
        ps.setString(1, email);
        ps.setString(2, hashedPass);
        ps.execute();
        return (true);
    }

    public static boolean validEmail(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from base_user where email=?" );
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        return (!rs.next());
    }

    public static int getUserId(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from base_user where email=?" );
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return (rs.getInt("id"));
    }

}
