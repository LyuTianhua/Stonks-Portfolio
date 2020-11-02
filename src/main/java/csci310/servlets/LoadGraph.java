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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@WebServlet("/LoadGraph")
public class LoadGraph extends HttpServlet {

    public static Database db;
    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static PrintWriter pw = null;
    public static SimpleDateFormat sdf;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        System.out.println("in load graph");
        try {

            sdf = new SimpleDateFormat("yyyy-MM-dd");
            pw = res.getWriter();
            int id = (int) req.getSession().getAttribute("id");

            String startDate = req.getParameter("fromGraph");
            String endDate = req.getParameter("toGraph");

            long startTimestamp = timestamp(startDate);
            long endTimestamp = timestamp(endDate);

            int startNthDay = nthDay(startDate);
            int endNthDay = nthDay(endDate);


            res.setContentType("application/json; charset=UTF-8");
            pw.print(graph(id, startTimestamp, endTimestamp, startNthDay, endNthDay));

            req.setAttribute("loaded", "true");
            pw.flush();
            pw.close();

        } catch (IOException | ParseException ignored) { }
    }

    public long timestamp(String date) throws ParseException {
        Date newDate = sdf.parse(date);
        return newDate.getTime() / 1000;
    }

    public int nthDay(String date) {
        String[] dateParts = date.split("-", 3);
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date(year, month, day));
        //note: probable error in day of year must subtract 30
        return cal.get(Calendar.DAY_OF_YEAR) - 30;
    }

    public static String graph(int id, long startTimestamp, long endTimestamp, int startNthDay, int endNthDay) {

        System.out.println("in graph function");

        String strValues = "";
        String strTimestamps = "";

        db = new Database();
        con = db.getConn();
        try {
            ps = con.prepareStatement("select * from base_user where id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            strValues = rs.getString("data");
            System.out.println("strValues:\t" + strValues);

            ps = con.prepareStatement("select * from company where id=?");
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            strTimestamps = rs.getString("timestamps");
            System.out.println("strTimestamps:\t" + strTimestamps);

        } catch (SQLException ignored) {}
        db.closeCon();

//        System.out.println("\n\n\n" + strValues + "\n\n\n");

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

            while (timestamps[i] < endTimestamp && i < timestamps.length-1) {
                vals.add(v[i]);
                d.add(timestamps[i]);
                i++;
            }

            values = new double[vals.size()];
            dates = new long[d.size()];

            for (int j = 0; j < vals.size(); j++)
                values[j] = vals.get(j);

            for (int j = 0; j < d.size(); j++)
                dates[j] = d.get(j);

        }
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

