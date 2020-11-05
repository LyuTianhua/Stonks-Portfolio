package csci310.servlets;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet("GraphHistorical")
public class GraphHistorical extends HttpServlet {

    public static PrintWriter pw;
    public static Database db;
    public static Connection con;
    public static PreparedStatement ps;
    public static ResultSet rs;


    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            pw = res.getWriter();

            String ticker = req.getParameter("ticker");
            boolean graph = Boolean.parseBoolean(req.getParameter("checked"));

            System.out.printf("in graph historical: %s, graph: %b\n", ticker, graph);

            if (graph) {

//                res.setContentType("application/json; charset=UTF-8");
//                res.setHeader("content-type", "application/json; charset=UTF-8");

                String startDate = req.getParameter("fromGraph");
                String endDate = req.getParameter("toGraph");

                System.out.println("start: " + startDate);
                System.out.println("end: " + endDate);

                long startTimestamp = LoadGraph.timestamp(startDate);
                long endTimestamp = LoadGraph.timestamp(endDate);

                String strValues = "";
                String strTimestamps = "";

                db = new Database();
                con = db.getConn();

                try {
                    ps = con.prepareStatement("select * from company where ticker=?");
                    ps.setString(1, ticker);
                    rs = ps.executeQuery();

                    if (!rs.next()) return;

                    strValues = rs.getString("data");
                    strTimestamps = rs.getString("timestamps");
                } catch (SQLException ignored) {}

                db.closeCon();

                System.out.printf("strValues: %s\nstrTimestamps: %s\nstartTimestamp: %d\nendTimestamp: %d\n", strValues, strTimestamps, startTimestamp, endTimestamp);

                String[] splitValues = strValues.split(" ", -1);
                String[] splitTimestamps = strTimestamps.split(" ", -1);

                int N = splitTimestamps.length - 1;

                double[] values = new double[N];
                long[] timestamps = new long[N];

                for (int i = 0; i < N; i++) {
                    values[i] = Double.parseDouble(splitValues[i]);
                    timestamps[i] = Long.parseLong(splitTimestamps[i]);
                }

                Gson gson = new Gson();
                if (!ticker.equals("LULU")) res.setContentType("application/json; charset=UTF-8");
                pw.println(gson.toJson(new DataSet(ticker, values, timestamps, startTimestamp, endTimestamp)));
                pw.flush();
                pw.close();

            } else {
                if (!ticker.equals("LULU")) res.setContentType("text/html");
                pw.println(ticker);
                pw.flush();
                pw.close();
            }

            req.setAttribute("loaded", true);
        } catch (Exception ignored) {}
    }

    private static class DataSet {
        public String label;
        public double[] data;
        public int borderWidth = 1;
        public String backgroundColor;
        public DataSet(String ticker, double[] values, long[] timestamps, long startTimestamp, long endTimestamp) {
            label = ticker;

            ArrayList<Double> tmpData = new ArrayList<>();

            int i = 0;
            while (timestamps[i] < startTimestamp)
                i++;

            while (timestamps[i] < endTimestamp && i < timestamps.length - 1)
                tmpData.add(values[i++]);

            data = new double[tmpData.size()];

            for (int j = 0; j < tmpData.size(); j++)
                data[j] = tmpData.get(j);

            Random rand = new Random();
            backgroundColor = String.format("rgba(%s, %s, %s, 0.2)\n", rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
        }
    }
}
