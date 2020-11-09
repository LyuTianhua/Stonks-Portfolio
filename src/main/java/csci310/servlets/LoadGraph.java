package csci310.servlets;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


@WebServlet("/LoadGraph")
public class LoadGraph extends HttpServlet {

    public static Database db;
    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static PrintWriter pw = null;
//    public static SimpleDateFormat sdf;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        System.out.println("in load graph");
        try {

//            sdf = new SimpleDateFormat("yyyy-MM-dd");
//            sdf.setLenient(false);

            pw = res.getWriter();
            HttpSession session = req.getSession();
            int id = (int) session.getAttribute("id");

            String startDate = req.getParameter("fromGraph");
            String endDate = req.getParameter("toGraph");


            System.out.printf("from: %s\n to: %s\n", startDate, endDate);

            long startTimestamp = timestamp(startDate);
            long endTimestamp = timestamp(endDate);

            if (id != 369) res.setContentType("application/json; charset=UTF-8");
            pw.print(graph(id, startTimestamp, endTimestamp));

            req.setAttribute("loaded", true);
            pw.flush();
            pw.close();

        } catch (IOException | ParseException ignored) { }
    }

    public static long timestamp(String date) throws ParseException {
        System.out.println("\n\n\n\n date: " + date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        sdf.setLenient(false);
        Date newDate = sdf.parse(date);
        return newDate.getTime() / 1000;
    }

    public static String graph(int id, long startTimestamp, long endTimestamp) {

        String strValues = "";
        String strTimestamps = "";

        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("select * from base_user where id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            strValues = rs.getString("data");

            ps = con.prepareStatement("select * from company ");
            rs = ps.executeQuery();
            rs.next();
            strTimestamps = rs.getString("timestamps");
            System.out.println("\n\n\n\nin load graph timestamp: " + strTimestamps + "\n\n\n");
        } catch (SQLException ignored) {}
        db.closeCon();

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

        return gson.toJson(new Graph(values, timestamps, startTimestamp, endTimestamp));
    }

    private static class Graph {
        double[] values;
        long[] dates;
        public Graph(double[] v, long[] timestamps, long startTimestamp, long endTimestamp) {

            ArrayList<Double> vals = new ArrayList<>();
            ArrayList<Long> d = new ArrayList<>();

            int i = 0;
            while (timestamps[i] < startTimestamp)
                i++;

            while (timestamps[i] < endTimestamp && i < timestamps.length-1) { vals.add(v[i]);d.add(timestamps[i]);i++; }

            values = new double[vals.size()];
            dates = new long[d.size()];

            for (int j = 0; j < vals.size(); j++) values[j] = vals.get(j);

            for (int j = 0; j < d.size(); j++) dates[j] = d.get(j);

        }
    }
}
