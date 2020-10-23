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
import java.util.ArrayList;


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
        Graph graph = new Graph();

        db = new Database();
        con = db.getConn();
        ps = con.prepareStatement("select company.data as cData, company.ticker as ticker, purchased, sold from stock left join company on stock.company_id = Company.id where user_id=?");
        ps.setInt(1, id);
        rs = ps.executeQuery();

        while (rs.next())
            addValues(graph, values,  rs.getString("cData"), rs.getString("ticker"));

        db.closeCon();

        Gson gson = new Gson();

        graph.addDataset(new DataSet("Total", values));

        return gson.toJson(graph);
    }

    public static void addValues(Graph graph, double[] values, String data, String ticker) {
        if (data.equals("")) return;

        double[] companyValues = new double[254];

        String[] splitData = data.split(" ", -1);
        for (int i = 0; i < values.length - 1; i++) {
            double d = Double.parseDouble(splitData[i]);
            values[i] += d;
            companyValues[i] = d;
        }

        graph.addDataset(new DataSet(ticker, companyValues));
    }

    private static class Graph {
        ArrayList<DataSet> dataSets;
        public Graph() { dataSets = new ArrayList<>(); }
        public void addDataset(DataSet ds) { dataSets.add(ds); }
    }
    private static class DataSet {
        String label;
        double[] data;
        public DataSet(String l, double[] d) {
            label = l;
            data = d;
        }
    }
}
