package by.epam.basavets.servlet.postVote;

import by.epam.basavets.command.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/post/vote")
public class SetPostVoteServlet extends HttpServlet {
    String title;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        title = req.getParameter("title");
        System.out.println(title);
        getServletContext().getRequestDispatcher("/setPostVote.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String value = req.getParameter("value");
        Command.getInstance().getPostVoteService().setPostVote(title, value);
        resp.setCharacterEncoding("UTF-8");
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
