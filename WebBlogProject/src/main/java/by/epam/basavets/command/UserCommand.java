package by.epam.basavets.command;

import by.epam.basavets.bean.User;
import by.epam.basavets.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserCommand {

    private final String ACTION = "action";
    private final String MODERATOR = "MODERATOR";
    private final String USER = "USER";
    private final String LOG_IN = "logIn";
    private final String REGISTRATION = "registration";

    private final String PASSWORD = "password";
    private final String EMAIL = "email";
    private final String AUTHORIZATION = "authorization";
    private final String NAME = "name";
    private final String SECRETE_CODE = "secreteCode";
    private final String REGISTRATION_ACCOUNT_USER = "registrationAccountUser";
    private final String AUTHORIZE_USER = "authorizeUser";
    private final String COMMAND = "command";


    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter(COMMAND);
//        if (command == null){
//            try {
//                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        if (command.equals("2")){
            try {
                if (req.getSession().getAttribute(AUTHORIZE_USER) == null) {

                    System.out.println(req.getSession().getAttribute(AUTHORIZE_USER));

                    req.getServletContext().getRequestDispatcher("/authorizationUser.jsp").forward(req, resp);
                } else {
                    User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
                    System.out.println(user.getName() + "Имя херня");
                    switch (user.getRole().getRoleTypes().toString()) {
                        case MODERATOR -> req.getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
                        case USER -> req.getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
                    }
                }
            } catch (Exception e) {
                //req.getServletContext().getRequestDispatcher("/authorizationUser.jsp").forward(req, resp);
            }
        }


    }


    public void actionUser(HttpServletRequest req, HttpServletResponse resp) {
        User user;

        String command = req.getParameter(COMMAND);

        String action = req.getParameter(ACTION);
        System.out.println(action);
        if (command.equals("20")){


        try {
            switch (action) {
                case LOG_IN: {
                    String password = req.getParameter(PASSWORD);
                    String email = req.getParameter(EMAIL);
                    HttpSession session = req.getSession();
                    user = ServiceFactory.getInstance().getUserService().authorizationUser(email, password);
                    if (user == null) {
                        session.setAttribute(AUTHORIZATION, "Авторизация не удалась");
                        System.out.println("Хуй");
                        req.getServletContext().getRequestDispatcher("/authorizationUser.jsp").forward(req, resp);
                    }
                    if (user != null) {
                        session.setAttribute(AUTHORIZATION, "Авторизация прошла успешно");


                        System.out.println("Пизда");
                        System.out.println(user.getName());

                        System.out.println(user.getRole().getRoleTypes());

                        switch (user.getRole().getRoleTypes().toString()) {
                            case MODERATOR -> req.getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
                            case USER -> req.getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
                        }

                        session.setAttribute(AUTHORIZE_USER, user);

                    }
                }
                break;
                case REGISTRATION: {
                    String name = req.getParameter(NAME);
                    String password = req.getParameter(PASSWORD);
                    String email = req.getParameter(EMAIL);
                    String secreteCode = req.getParameter(SECRETE_CODE);
                    HttpSession session = req.getSession();
                    User newUser = ServiceFactory.getInstance().getUserService().findUserByEmail(email);
                    if (name.length() == 0) {
                        session.setAttribute(REGISTRATION_ACCOUNT_USER, "Имя должно быть указано");
                    } else if (password.length() < 6) {
                        session.setAttribute(REGISTRATION_ACCOUNT_USER, "Пароль слишком короткий");

                    } else if (newUser != null) {
                        session.setAttribute(REGISTRATION_ACCOUNT_USER, "Пользователь с таким адресом электронной почты " +
                                "уже зарегистрирован");
                    } else if (!ServiceFactory.getInstance().getUserService().isValidEmail(email)) {
                        session.setAttribute(REGISTRATION_ACCOUNT_USER, "Неккоректный адрес электронной почты");
                    } else {
                        session.setAttribute(REGISTRATION_ACCOUNT_USER, "Пользователь успешно зарегистрирован");
                        ServiceFactory.getInstance().getUserService().addRole(secreteCode);
                        ServiceFactory.getInstance().getUserService().registerUser(name, password, email);
                    }
                    req.getServletContext().getRequestDispatcher("/authorizationUser.jsp").forward(req, resp);
                }
                break;
            }
        } catch (Exception e) {
            throw new CommandProviderException(e.getMessage());
            //e.printStackTrace();
        }
        }
    }

    public static UserCommand getInstance(){
        return new UserCommand();
    }
    //}
}
