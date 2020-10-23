//package csci310.servlets;
//
//import com.google.gson.Gson;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//@WebServlet("/AddStockToGraph")
//public class AddStockToGraph extends HttpServlet {
//
//    public static Database db;
//    public static Connection con = null;
//    public static PreparedStatement ps = null;
//    public static ResultSet rs = null;
//    public static PrintWriter pw = null;
//
//    public void doGet(HttpServletRequest req, HttpServletResponse res) {
//
//        System.out.println("\n\n\n add stock to graph\n\n");
//
//        try {
//            pw = res.getWriter();
//            String ticker = req.getParameter("ticker");
//
//            res.setContentType("application/json; charset=UTF-8");
//            pw.print(graph(ticker));
//
//            pw.flush();
//            pw.close();
//
//        } catch (SQLException | IOException ignored) { }
//    }
//
//
//    public static String graph(String ticker) throws SQLException {
//
//        double[] values = new double[254];
//
//        db = new Database();
//        con = db.getConn();
//        ps = con.prepareStatement("select data from company where ticker=?");
//        ps.setString(1, ticker);
//        rs = ps.executeQuery();
//
//        if (!rs.next() || rs.getString("data") == null)
//            return "";
//
//        String data = rs.getString("data");
//        String[] splitData = data.split(" ", -1);
//        for (int i = 0; i < values.length - 1; i++)
//            values[i] += Double.parseDouble(splitData[i]);
//
//        db.closeCon();
//
//        Gson gson = new Gson();
//
//        return gson.toJson(new Graph(values));
//    }
//
//    private static class Graph {
//        double[] values;
//        public Graph(double[] v) { values = v; }
//    }
//
//
//}
