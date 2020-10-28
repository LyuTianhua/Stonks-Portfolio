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
import java.util.Arrays;


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

            req.setAttribute("loaded", "true");
            pw.flush();
            pw.close();

        } catch (IOException ignored) { }
    }


    public static String graph(int id) {
        Graph graph = new Graph();

        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("select company.data as cData, company.ticker as ticker, purchased, sold from stock left join company on stock.company_id = Company.id where user_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next())
                addValues(graph, rs.getString("cData"), rs.getString("ticker"));

        } catch (SQLException ignored) {}
        db.closeCon();

        Double[] values = new Double[365];
        Arrays.fill(values, 0d);

        for (DataSet ds : graph.dataSets)
            for (int i = 0; i < ds.data.length; i++)
                values[i] += ds.data[i];

        graph.addDataset(new DataSet("Total", values));

        Gson gson = new Gson();
        return gson.toJson(graph);
    }

    public static void addValues(Graph graph, String data, String ticker) {
        if (data.equals("")) return;

        ArrayList<Double> companyValues = new ArrayList<>();

        String[] splitData = data.split(" ", -1);
        for (int i = 0; i < splitData.length - 1; i++) {
            double d = Double.parseDouble(splitData[i]);
            companyValues.add(d);
//
//            if (i % 5 == 0) {
//                companyValues.add(d);
//                companyValues.add(d);
//            }
//
//            if (i % 35 == 0)
//                companyValues.add(d);
        }

//        companyValues.add(companyValues.get(companyValues.size()-1));
//        companyValues.add(companyValues.get(companyValues.size()-1));

        Double[] companyValuesArray = companyValues.toArray(new Double[0]);

        graph.addDataset(new DataSet(ticker, companyValuesArray));
    }

    private static class Graph {
        public ArrayList<DataSet> dataSets;
        public Graph() { dataSets = new ArrayList<>(); }
        public void addDataset(DataSet ds) { dataSets.add(ds); }
    }
    private static class DataSet {
        public String label;
        public Double[] data;
        public DataSet(String l, Double[] d) {
            label = l;
            data = d;
        }
    }
}
