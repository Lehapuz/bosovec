package by.epam.basavets.servlet;

import by.epam.basavets.bean.User;
import by.epam.basavets.command.Command;
import by.epam.basavets.utils.Filter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/getUsers")
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

        Command command = new Command();
        //StringBuilder stringBuilder = new StringBuilder();

        //super.doGet(req, resp);
        //PrintWriter writer = resp.getWriter();
        //command.getUserService().getAllUsers().forEach(user -> stringBuilder.append(user.toString()));
        //writer.println(stringBuilder);
        HttpSession session = req.getSession();
        try {
            if (command.getUserService().getAllUsers().size() == 0){
                session.setAttribute("user", "Пользователи отсутствуют");
            }
            else {
                session.setAttribute("user", command.getUserService().getAllUsers());
            }
            resp.setCharacterEncoding("UTF-8");
            getServletContext().getRequestDispatcher("/listUser.jsp").forward(req, resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
