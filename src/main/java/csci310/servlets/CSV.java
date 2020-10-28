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

@WebServlet("/CSV")
public class CSV extends HttpServlet {

    RequestDispatcher dispatcher;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {

            int id = (int) req.getSession().getAttribute("id");
            String path = req.getParameter("path");

            System.out.println(path);

            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            String[] data;

            dispatcher = req.getRequestDispatcher("/AddStock");

            while ((line = br.readLine()) != null) {
                data = line.split(",");
                req.setAttribute("ticker", data[0]);
                req.setAttribute("quantity", data[1]);
                req.setAttribute("purchased", data[2]);
                req.setAttribute("sold", data[3]);
                dispatcher.include(req, res);
                req.removeAttribute("ticker");
                req.removeAttribute("quantity");
                req.removeAttribute("purchased");
                req.removeAttribute("sold");
            }
            br.close();
        } catch (IOException | ServletException ignored) { }

    }
}