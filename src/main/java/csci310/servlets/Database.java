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

public class Database {
    public Connection con = null;
    public Connection getConn() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:csci310.db");
        } catch (SQLException e) { con = null; }
        return con;
    }

    public void closeCon() {
        try {
            con.close();
        } catch (SQLException e) {}
    }
}
