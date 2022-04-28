package by.epam.basavets.servlet;

import by.epam.basavets.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/getUsers")
public class UserServlet extends HttpServlet{

//public UserServlet() {
        //    super();
        //}

        //@Override
        //public void init(ServletConfig config) throws ServletException {
        //    super.init(config);
        //}

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            Command command = new Command();

            //super.doGet(req, resp);
            PrintWriter writer = resp.getWriter();
            try {
               command.getUserService().getAllUsers();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            };
            //RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
            //requestDispatcher.forward(req, resp);

    }
}
