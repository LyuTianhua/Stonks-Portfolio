package csci310.servlets;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/LoadGraph")
public class LoadGraph extends HttpServlet {

    public static Database db;
    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static PrintWriter pw = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            pw = res.getWriter();
            int id = (int) req.getSession().getAttribute("id");

            res.setContentType("application/json; charset=UTF-8");
            pw.print(graph(id));

            pw.flush();
            pw.close();

        } catch (SQLException | IOException ignored) { }
    }


    public static String graph(int id) throws SQLException {

        double[] values = new double[254];

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select company.data as cData, purchased, sold from stock left join company on stock.company_id = Company.id where user_id=?");
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.next())
            addValues(values,  rs.getString("cData"));

        db.closeCon();

        Gson gson = new Gson();

        return gson.toJson(new Graph(values));
    }

    public static void addValues(double[] values, String data) {
        if (data.equals("")) return;

        String[] splitData = data.split(" ", -1);
        for (int i = 0; i < values.length - 1; i++)
            values[i] += Double.parseDouble(splitData[i]);


    }

    private static class Graph {
        double[] values;
        public Graph(double[] v) { values = v; }
    }

}
