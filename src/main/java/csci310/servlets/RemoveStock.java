package csci310.servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

@WebServlet("/RemoveStock")
public class RemoveStock extends HttpServlet {

    static Database db;
    static Connection con;
    static ResultSet rs;
    static PreparedStatement ps;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("in remove stock");

        int userId = (int) req.getSession().getAttribute("id");
        int stockID = Integer.parseInt(req.getParameter("ticker_id"));

        System.out.println("stock: " + stockID);
//            double quantity = Double.parseDouble(req.getParameter("quantity"));
        updateStock(userId, stockID);
    }

    public static void updateStock(int userId, int stockId) {

        System.out.printf("user id: %d\nstock id : %d\n", userId, stockId);

        db = new Database();
        con = db.getConn();

        try {
        	// Get info from stock entry
            ps = con.prepareStatement("select * from Stock where id=?");
            ps.setInt(1, stockId);
            rs = ps.executeQuery();
            
            int companyID = rs.getInt("company_id");
            long purchasedDate = rs.getLong("purchased");
            long soldDate = rs.getLong("sold");
        	
            // Get user portfolio value
            ps = con.prepareStatement("select * from base_user where id=?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            String userData = rs.getString("data");

            String[] splitUserData = userData.split(" ", -1);
            int N = splitUserData.length - 1;
            
            // Get required stock value
            ps = con.prepareStatement("select * from Company where id=?");
            ps.setInt(1, companyID);
            rs = ps.executeQuery();
            Blob blob = rs.getBlob("timestamps");
            String companyTimestamp = new String(blob.getBytes(1, (int) blob.length()));
            String companyData = rs.getString("data");
            
            String[] splitTimestamps = companyTimestamp.split(" ", -1);
            String[] splitCompanyValues = companyData.split(" ", -1);
            
            double[] doubleCompanyValues = new double[N];
            long[] longTimestamps = new long[N];
            
            for (int i = 0; i < N; i++) {
                doubleCompanyValues[i] = Double.parseDouble(splitCompanyValues[i]);
                longTimestamps[i] = Long.parseLong(splitTimestamps[i]);
            }

            // Update user data
            double[] data = new double[N];
            for (int i = 0; i < N; i++) data[i] = Double.parseDouble(splitUserData[i]);

            int k = 0;
            while (purchasedDate >= longTimestamps[k]) k++;


            while (soldDate >= longTimestamps[k]) {
                data[k] -= doubleCompanyValues[k];
                k++;
            }

            StringBuilder newUserData = new StringBuilder();
            for (int i = 0; i < N; i++) newUserData.append(data[i]).append(" ");
            
            // Apply the change to the database
            ps = con.prepareStatement("update base_user set data=? where id=?");
            ps.setString(1, newUserData.toString());
            ps.setInt(2, userId);
            ps.executeUpdate();
            

        	// Delete stock entry
            ps = con.prepareStatement("delete from Stock where id=? and user_id=?");
            ps.setInt(1, stockId);
            ps.setInt(2, userId);
            ps.execute();
            
            System.out.println("executed update stock query");
        } catch (SQLException e) {e.printStackTrace();}
        db.closeCon();
    }

}
