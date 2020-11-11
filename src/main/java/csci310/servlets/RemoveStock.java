package csci310.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/RemoveStock")
public class RemoveStock extends HttpServlet {

    static Database db;
    static Connection con;
    static ResultSet rs;
    static PreparedStatement ps;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        int stockId = Integer.parseInt(req.getParameter("ticker"));
        int user_id = (int) req.getSession().getAttribute("id");
        int company_id = 0;

        String strData = "";
        String strCmpData = "";
        String strTimestamps = "";

        String[] splitData;
        String[] splitCmpData;
        String[] splitTimestamps;

        Double[] data;

        long purchased = 0;
        long sold = 0;

        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("select * from base_user where id=?");
            ps.setInt(1, user_id);
            rs = ps.executeQuery();
            rs.next();
            strData = rs.getString("data");

            ps = con.prepareStatement("select * from stock where id=?");
            ps.setInt(1, stockId);
            rs = ps.executeQuery();
            rs.next();
            purchased = rs.getLong("purchased");
            sold = rs.getLong("sold");
            int companyId = rs.getInt("company_id");

            ps = con.prepareStatement("select * from company where id=?");
            ps.setInt(1, companyId);
            rs = ps.executeQuery();
            rs.next();
            company_id = rs.getInt("id");
            strCmpData = rs.getString("data");
            strTimestamps = rs.getString("timestamps");

            ps = con.prepareStatement("delete from Stock where id=?");
            ps.setInt(1, stockId);
            ps.execute();
        } catch (SQLException ignored) {}
        db.closeCon();

        splitData = strData.split(", ", -1);
        splitCmpData = strCmpData.split(" ", -1);
        splitTimestamps = strTimestamps.split(" ", -1);

        data = new Double[splitData.length];
        Arrays.fill(data, 0d);

        for (int i = 0; i < splitData.length - 1; i++)
            data[i] = Double.parseDouble(splitData[i]);

        long ts;
        for (int i = 0; i < splitCmpData.length - 1; i++) {
            ts = Long.parseLong(splitTimestamps[i]);
            if (purchased < ts && ts <= sold)
                data[i] -= Double.parseDouble(splitCmpData[i]);
        }

        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("update base_user set data=? where id=?");
            ps.setString(1, Arrays.toString(data).replace("[", "").replace("]", ""));
            ps.setInt(2, user_id);
            ps.executeUpdate();
        } catch (SQLException ignored) {}
        db.closeCon();

        req.setAttribute("removed", true);
    }
}
