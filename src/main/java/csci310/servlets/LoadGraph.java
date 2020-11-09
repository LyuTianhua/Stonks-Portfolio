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
import java.util.Date;
import java.util.Random;


@WebServlet("/LoadGraph")
public class LoadGraph extends HttpServlet {

    public static Database db;
    public static Connection con = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    public static PrintWriter pw = null;
//    public static SimpleDateFormat sdf;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            int id = (int) req.getSession().getAttribute("id");

            pw = res.getWriter();
            pw.print(graph(id));
            pw.flush();
            pw.close();

            req.setAttribute("loaded", true);

        } catch (IOException ignored) { }
    }

    public static long timestamp(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = sdf.parse(date);
        return newDate.getTime() / 1000;
    }

    public static String graph(int id) {

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

        } catch (SQLException ignored) {}
        db.closeCon();

        strValues = strValues.replace("[", "").replace("]", "");

        String[] splitValues = strValues.split(", ", -1);

        String[] splitTimestamps = strTimestamps.split(" ", -1);

        int N = splitTimestamps.length - 1;

        double[] values = new double[N];
        long[] timestamps = new long[N];

        for (int i = 0; i < N; i++) {
            values[i] = Double.parseDouble(splitValues[i]);
            timestamps[i] = Long.parseLong(splitTimestamps[i]);
        }

        ArrayList<DataSet> datasets = new ArrayList<>();

        datasets.add(new DataSet("portfolio", values, false, "rgba(130, 195, 100, 1)"));

        db = new Database();
        con = db.getConn();
        try {

            ps = con.prepareStatement("select Company.ticker as ticker, Company.data as data from historicalStock left join Company on historicalStock.company_id=Company.id where user_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            Random rand = new Random();
            int r, g, b;
            String ticker, strHistoricalData;
            String[] splitHistoricalData;
            double[] historicalData;
            while (rs.next()) {

                r = rand.nextInt(255);
                b = rand.nextInt(255);

                ticker = rs.getString("ticker");
                strHistoricalData = rs.getString("data");

                splitHistoricalData = strHistoricalData.split(" ", -1);
                historicalData = new double[splitHistoricalData.length - 1];

                for (int i = 0; i < splitHistoricalData.length - 1; i++)
                    historicalData[i] = Double.parseDouble(splitHistoricalData[i]);

                datasets.add(new DataSet(ticker, historicalData, true, String.format("rgba(%d, 0, %d, 1)\n", r, b)));
            }

        } catch (SQLException ignored) {}
        db.closeCon();

        Gson gson = new Gson();
        return gson.toJson(new Data(datasets, timestamps));
    }

    private static class Data {
        DataSet[] datasets;
        long[] timestamps;
        public Data(ArrayList<DataSet> ds, long[] ts) {
            timestamps = ts;
            datasets = new DataSet[ds.size()];
            for (int i = 0; i < ds.size(); i++)
                datasets[i] = ds.get(i);
        }
    }

    private static class DataSet {
        public String label;
        public double[] data;
        public int borderWidth = 2;
        public boolean hidden;
        public String borderColor = "";
        public String backgroundColor = "rgba(0, 0, 0, 0)";
        public DataSet(String ticker, double[] values, boolean h, String bc) {
            borderColor = bc;
            hidden = h;
            label = ticker;
            data = new double[values.length];
            System.arraycopy(values, 0, data, 0, data.length);
        }
    }
}

