package by.epam.basavets.web.servlet;

import by.epam.basavets.bean.User;
import by.epam.basavets.controller.DefaultController;
import by.epam.basavets.dao.UserDAO;
import by.epam.basavets.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/add")
public class UserServlet extends HttpServlet {
    //public UserServlet() {
    //    super();
    //}

    //@Override
    //public void init(ServletConfig config) throws ServletException {
    //    super.init(config);
    //}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        PrintWriter writer = resp.getWriter();
        writer.println("Method GET from AddServlet");
        //RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        //requestDispatcher.forward(req, resp);
    }
}
