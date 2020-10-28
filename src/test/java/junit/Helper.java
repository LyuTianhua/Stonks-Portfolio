package junit;

import csci310.servlets.Database;
import org.mockito.Mockito;

import java.sql.*;

public class Helper extends Mockito {

    static Database db;
    static Connection con;
    static PreparedStatement ps;

    public static void insert_stock_company_user_shares(int company_id, int user_id, double shares) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("insert into stock (company_id, user_id, shares, purchased, sold) values (?, ?, ?, ?, ?)");
            ps.setInt(1, company_id);
            ps.setInt(2, user_id);
            ps.setDouble(3, shares);
            ps.setDate(4, new Date(2020, 10, 10));
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void insert_user_name_password(String user, String password) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("insert into base_user (email, password) values (?, ?)");
            ps.setString(1, user);
            ps.setString(2, password);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void insert_user_id_name_password(int id, String name, String password) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("insert into base_user (id, email, password) values (?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.execute();
        } catch (SQLException sql ) {
            sql.printStackTrace();
        }
        db.closeCon();
    }

    public static void insert_company_id_ticker(int id, String ticker) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("insert into company (id, ticker) values (?, ?)");
            ps.setInt(1, id);
            ps.setString(2, ticker);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_user_where_name(String name) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from base_user where email=?");
            ps.setString(1, name);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_user_where_id(int id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from user where id=?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_from_stock_user_company(int user_id, int company_id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from stock where user_id=? and company_id=?");
            ps.setInt(1, user_id);
            ps.setInt(2, company_id);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_from_stock_where_id(int user_id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from stock where user_id=?");
            ps.setInt(1, user_id);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_company_where_id(int id) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from company where id=?");
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }

    public static void delete_company_where_ticker(String ticker) {
        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("delete from company where ticker=?");
            ps.setString(1, ticker);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();
    }
}
