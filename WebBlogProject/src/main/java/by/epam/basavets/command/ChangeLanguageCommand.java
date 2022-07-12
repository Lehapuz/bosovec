package by.epam.basavets.command;

import by.epam.basavets.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand {


    private final String MODERATOR = "MODERATOR";
    private final String USER = "USER";
    private final String LOCAL = "local";
    private final String AUTHORIZE_USER = "authorizeUser";
    private final String COMMAND = "command";



    public void changeLanguageGet(HttpServletRequest req, HttpServletResponse resp) {
        String command = req.getParameter(COMMAND);
        System.out.println(command);
//        if (command == null){
//            try {
//                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        if (command.equals("1")){

        try {

            req.getServletContext().getRequestDispatcher("/lang.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }


    public void changeLanguagePost(HttpServletRequest req, HttpServletResponse resp) {
        //String command = req.getParameter(COMMAND);

        //if (command == null){
        //    try {
        //        req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        //    } catch (Exception e) {
        //        e.printStackTrace();
        //    }
        //}

        //else if (command.equals("20")){

        //    System.out.println("aaaaaaaaaaaaaaaaaaa");
            req.getSession(true).setAttribute(LOCAL, req.getParameter(LOCAL));
            try {
                if (req.getSession().getAttribute(AUTHORIZE_USER) == null) {
                    req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                } else {
                    User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
                    switch (user.getRole().getRoleTypes().toString()) {
                        case MODERATOR -> req.getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
                        case USER -> req.getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
                    }
                }
            } catch (Exception e) {
                throw new CommandProviderException(e.getMessage());
            }
        }
    //}



}
