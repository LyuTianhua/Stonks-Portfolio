package csci310.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;

import com.google.gson.Gson;

@WebServlet("/CSV")
public class CSV extends HttpServlet {

    RequestDispatcher dispatcher;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        try {
            String path = req.getParameter("path");

            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br1 = new BufferedReader(fr);

            String line = "";
            String[] data;

            dispatcher = req.getRequestDispatcher("/AddStock");

            PrintWriter pw = res.getWriter();

            ArrayList<String> tickers = new ArrayList<>();
            ArrayList<String> quantity = new ArrayList<>();
            ArrayList<String> purchased = new ArrayList<>();
            ArrayList<String> sold = new ArrayList<>();
            int size = 0;

            while ((line = br1.readLine()) != null) {
                data = line.split(",");
                tickers.add(data[0]);
                quantity.add(data[1]);
                purchased.add(data[2]);
                sold.add(data[3]);
                ++size;
                if (!TickerChecking.checkTicker(data[0])) {
                    res.addHeader("error", data[0]);
                    res.sendError(404);
                    return;
                }

                try {
                    // Check dates are valid
                    Date purchasedDate = new SimpleDateFormat("yyyy-MM-dd").parse(data[2]);
                    Date soldDate = new SimpleDateFormat("yyyy-MM-dd").parse(data[3]);
                    if (purchasedDate.after(soldDate)) {
                        res.addHeader("error", data[0]);
                        res.sendError(405);
                        return;
                    }
                } catch(Exception e) {}

                if(Integer.parseInt(data[1]) < 1) {
                    res.addHeader("error", data[0]);
                    res.sendError(406);
                    return;
                }
            }

            for (int i = 0; i < size; i++) {
                req.setAttribute("ticker", tickers.get(i));
                req.setAttribute("quantity", quantity.get(i));
                req.setAttribute("purchased", purchased.get(i));
                req.setAttribute("sold", sold.get(i));
                dispatcher.include(req, res);
                req.removeAttribute("ticker");
                req.removeAttribute("quantity");
                req.removeAttribute("purchased");
                req.removeAttribute("sold");
            }

            br1.close();
        } catch (IOException | ServletException ignored) { }

    }
}