package csci310.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CSV")
public class CSV extends HttpServlet {

    RequestDispatcher dispatcher;

    public void doGet(HttpServletRequest req, HttpServletResponse res) {

    }
}