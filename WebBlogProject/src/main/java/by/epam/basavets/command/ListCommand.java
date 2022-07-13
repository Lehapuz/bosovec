package by.epam.basavets.command;

import by.epam.basavets.bean.ModeratorStatus;
import by.epam.basavets.bean.Post;
import by.epam.basavets.bean.PostComment;
import by.epam.basavets.bean.User;
import by.epam.basavets.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListCommand {

    private final String ACTION = "action";
    private final String MODERATOR = "MODERATOR";
    private final String USER = "USER";
    private final String LOG_IN = "logIn";
    private final String REGISTRATION = "registration";
    private final String UPDATE_USER = "updateUser";
    private final String DELETE_USER = "deleteUser";
    private final String ADD_POST = "addPost";
    private final String UPDATE_POST = "updatePost";
    private final String DELETE_POST = "deletePost";
    private final String ADMIT = "admit";
    private final String EXIT = "exit";
    private final String YES = "Yes";
    private final String NO = "No";
    private final String SETTINGS = "settings";
    private final String STATUS = "status";
    private final String POST_ID = "postId";
    private final String VALUE = "value";
    private final String UPDATE_COMMENT = "updateComment";
    private final String DELETE_COMMENT = "deleteComment";
    private final String LOCAL = "local";
    private final String SIZE = "size";
    private final String POSTS = "posts";
    private final String PASSWORD = "password";
    private final String EMAIL = "email";
    private final String AUTHORIZATION = "authorization";
    private final String NAME = "name";
    private final String SECRETE_CODE = "secreteCode";
    private final String NEW_PASSWORD = "newPassword";
    private final String REGISTRATION_ACCOUNT_USER = "registrationAccountUser";
    private final String UPDATE_ACCOUNT_USER = "updateAccountUser";
    private final String DELETE_ACCOUNT_USER = "deleteAccountUser";
    private final String MY_POSTS = "myPosts";
    private final String TITLE = "title";
    private final String TEXT = "text";
    private final String UPDATE_TITLE = "updateTitle";
    private final String NEW_TITLE = "newTitle";
    private final String UPDATE_TEXT = "updateText";
    private final String DELETE_TITLE = "deleteTitle";
    private final String CORRECT_EMAIL = "correctEmail";
    private final String UPDATE_POST_TEXT = "updatePostText";
    private final String DELETE_POST_TEXT = "deletePostText";
    private final String POST = "post";
    private final String POST_COMMENT_SIZE = "postCommentSize";
    private final String POST_COMMENTS = "postComments";
    private final String TEXT_COMMENT = "textComment";
    private final String POST_COMMENT = "postComment";
    private final String NEW_TEXT_COMMENT = "newTextComment";
    private final String DELETE_TEXT_COMMENT = "deleteTextComment";
    private final String UPDATE_TEXT_COMMENT = "updateTextComment";
    private final String DELETE_EMAIL = "deleteEmail";
    private final String AUTHORIZE_USER = "authorizeUser";
    private final String ADD_COMMENT = "addComment";


    public void getPosts(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<Post> posts;
        List<Post> correctPosts;
        try {
            posts = ServiceFactory.getInstance().getPostService().getAllPosts();
            correctPosts = posts.stream().filter(post -> post.getModeratorStatus().equals(ModeratorStatus.ACCEPTED))
                    .collect(Collectors.toList());
            if (req.getSession().getAttribute(AUTHORIZE_USER) == null) {
                int size = correctPosts.size();
                session.setAttribute(SIZE, size);
                session.setAttribute(POSTS, correctPosts);
                req.getServletContext().getRequestDispatcher("/listPost.jsp").forward(req, resp);
            } else {
                User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
                switch (user.getRole().getRoleTypes().toString()) {
                    case MODERATOR -> {
                        int size = posts.size();
                        session.setAttribute(SIZE, size);
                        session.setAttribute(POSTS, posts);
                        req.getServletContext().getRequestDispatcher("/listModeratorPost.jsp").forward(req, resp);
                    }
                    case USER -> {
                        int size1 = correctPosts.size();
                        session.setAttribute(SIZE, size1);
                        session.setAttribute(POSTS, correctPosts);
                        req.getServletContext().getRequestDispatcher("/listUserPost.jsp").forward(req, resp);
                    }
                }
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void actionUser(HttpServletRequest req, HttpServletResponse resp) {
        User user;
        String action = req.getParameter(ACTION);
        try {
            switch (action) {
                case LOG_IN: {
                    String password = req.getParameter(PASSWORD);
                    String email = req.getParameter(EMAIL);
                    HttpSession session = req.getSession();
                    user = ServiceFactory.getInstance().getUserService().authorizationUser(email, password);
                    if (user == null) {
                        session.setAttribute(AUTHORIZATION, "Авторизация не удалась");
                        req.getServletContext().getRequestDispatcher("/authorizationUser.jsp").forward(req, resp);
                    }
                    if (user != null) {
                        session.setAttribute(AUTHORIZATION, "Авторизация прошла успешно");
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
            throw new CommandProviderException(e);
        }
    }


    public void getUpdateUserPage(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
        try {
            if (user == null) {
                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                req.getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void updateUser(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter(ACTION);
        try {
            if (UPDATE_USER.equals(action)) {
                String email = req.getParameter(EMAIL);
                String password = req.getParameter(PASSWORD);
                String name = req.getParameter(NAME);
                String newPassword = req.getParameter(NEW_PASSWORD);
                HttpSession session = req.getSession();
                User updateUser = (User) session.getAttribute(AUTHORIZE_USER);
                if (ServiceFactory.getInstance().getUserService().findUserByEmail(email) == null ||
                        !updateUser.getEmail().equals(ServiceFactory.getInstance().getUserService().findUserByEmail(email).getEmail())) {
                    session.setAttribute(UPDATE_ACCOUNT_USER, "Неверный адрес электронной почты");
                    req.getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
                } else if (!updateUser.getPassword().equals(password)) {
                    session.setAttribute(UPDATE_ACCOUNT_USER, "Неверный пароль");
                    req.getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
                } else if (name.length() == 0) {
                    session.setAttribute(UPDATE_ACCOUNT_USER, "Имя должно быть указано");
                    req.getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
                } else if (newPassword.length() < 6) {
                    session.setAttribute(UPDATE_ACCOUNT_USER, "Новый пароль слишком короткий");
                    req.getServletContext().getRequestDispatcher("/updateUser.jsp").forward(req, resp);
                } else {
                    session.setAttribute(UPDATE_ACCOUNT_USER, "Аккаунт пользователя успешно обновлен");
                    ServiceFactory.getInstance().getUserService().updateUserByEmail(updateUser, email, password, name, newPassword);
                    switch (updateUser.getRole().getRoleTypes().toString()) {
                        case MODERATOR -> req.getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
                        case USER -> req.getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
                    }
                }
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void getDeleteUserPage(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
        try {
            if (user == null) {
                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                req.getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter(ACTION);
        try {
            if (DELETE_USER.equals(action)) {
                String email = req.getParameter(EMAIL);
                String password = req.getParameter(PASSWORD);
                HttpSession session = req.getSession();
                User deleteUser = (User) session.getAttribute(AUTHORIZE_USER);
                if (ServiceFactory.getInstance().getUserService().findUserByEmail(email) == null ||
                        !deleteUser.getEmail().equals(ServiceFactory.getInstance().getUserService()
                                .findUserByEmail(email).getEmail())) {
                    session.setAttribute(DELETE_ACCOUNT_USER, "Неверный адрес электронной почты");
                    req.getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
                } else if (!deleteUser.getPassword().equals(password)) {
                    session.setAttribute(DELETE_ACCOUNT_USER, "Неверный пароль");
                    req.getServletContext().getRequestDispatcher("/deleteUser.jsp").forward(req, resp);
                } else {
                    session.setAttribute(DELETE_ACCOUNT_USER, "Аккаунт пользователя успешно удален");
                    ServiceFactory.getInstance().getUserService().deleteUserByEmail(deleteUser, email, password);
                    session.setAttribute(AUTHORIZE_USER, null);
                    req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getSession().getAttribute(AUTHORIZE_USER) == null) {
                req.getServletContext().getRequestDispatcher("/authorizationUser.jsp").forward(req, resp);
            } else {
                User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
                switch (user.getRole().getRoleTypes().toString()) {
                    case MODERATOR -> req.getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
                    case USER -> req.getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            req.getServletContext().getRequestDispatcher("/authorizationUser.jsp").forward(req, resp);
        }
    }


    public void changeLanguageGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getServletContext().getRequestDispatcher("/lang.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void changeLanguage(HttpServletRequest req, HttpServletResponse resp) {
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
            throw new CommandProviderException(e);
        }
    }


    public void getMyPosts(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
        try {
            if (user != null) {
                List<Post> posts;
                List<Post> myPosts = new ArrayList<>();
                posts = ServiceFactory.getInstance().getPostService().getAllPosts();
                for (Post post : posts) {
                    if (user.getId().equals(post.getUser().getId())) {
                        myPosts.add(post);
                    }
                }
                HttpSession session = req.getSession();
                session.setAttribute(MY_POSTS, myPosts);
                req.getServletContext().getRequestDispatcher("/listMyPost.jsp").forward(req, resp);
            } else {
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void actionMyPosts(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
        String action = req.getParameter(ACTION);
        String email = req.getParameter(EMAIL);
        String title = req.getParameter(TITLE);
        String text = req.getParameter(TEXT);
        String updateTitle = req.getParameter(UPDATE_TITLE);
        String newTitle = req.getParameter(NEW_TITLE);
        String updateText = req.getParameter(UPDATE_TEXT);
        String deleteTitle = req.getParameter(DELETE_TITLE);
        try {
            switch (action) {
                case ADD_POST: {
                    if (ServiceFactory.getInstance().getSettingsService().showSettings().toString().equals("No")) {
                        session.setAttribute(CORRECT_EMAIL, "На добавление новых постов наложен запрет");
                    } else if (ServiceFactory.getInstance().getUserService().findUserByEmail(email) == null ||
                            !user.getEmail()
                                    .equals(ServiceFactory.getInstance().getUserService().findUserByEmail(email).getEmail())) {
                        session.setAttribute(CORRECT_EMAIL, "Адрес электронной почты указан неверно");
                    } else {
                        ServiceFactory.getInstance().getPostService().addNewPost(email, title, text);
                        session.setAttribute(CORRECT_EMAIL, "Пост успешно добавлен");
                    }
                }
                case UPDATE_POST: {
                    Post post = ServiceFactory.getInstance().getPostService().getPostByTitle(updateTitle);
                    if (post == null || !user.getId().equals(post.getUser().getId())) {
                        session.setAttribute(UPDATE_POST_TEXT, "Введено неверное название поста");
                    } else {
                        session.setAttribute(UPDATE_POST_TEXT, "Пост пользователя успешно обновлен");
                        ServiceFactory.getInstance().getPostService().updatePostByTitle(updateTitle, newTitle, updateText);
                    }
                }
                case DELETE_POST: {
                    Post post = ServiceFactory.getInstance().getPostService().getPostByTitle(deleteTitle);
                    if (post == null || !user.getId().equals(post.getUser().getId())) {
                        session.setAttribute(DELETE_POST_TEXT, "Введено неверное название поста");
                    } else {
                        session.setAttribute(DELETE_POST_TEXT, "Пост пользователя успешно удален");
                        ServiceFactory.getInstance().getPostService().deletePostByTitle(deleteTitle);
                    }
                }
            }
            req.getServletContext().getRequestDispatcher("/listMyPost.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void getExitPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getServletContext().getRequestDispatcher("/exitUser.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void exitUser(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
        try {
            if (user == null) {
                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                String action = req.getParameter(ACTION);
                if (EXIT.equals(action)) {
                    String admit = req.getParameter(ADMIT);
                    switch (admit) {
                        case YES -> {
                            session.setAttribute(AUTHORIZE_USER, null);
                            req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
                        }
                        case NO -> {
                            switch (user.getRole().getRoleTypes().toString()) {
                                case MODERATOR -> req.getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
                                case USER -> req.getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void getSettings(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
        try {
            if (user != null) {
                session.setAttribute(SETTINGS, ServiceFactory.getInstance().getSettingsService().showSettings());
                req.getServletContext().getRequestDispatcher("/setSettings.jsp").forward(req, resp);
            } else {
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void setSettings(HttpServletRequest req, HttpServletResponse resp) {
        String status = req.getParameter(STATUS);
        try {
            ServiceFactory.getInstance().getSettingsService().setSettings(status);
            req.getServletContext().getRequestDispatcher("/moderator.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void getPostById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            String postId = req.getParameter(POST_ID);
            Post postById = ServiceFactory.getInstance().getPostService().getPostById(postId);
            if (postById == null) {
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            } else {
                ServiceFactory.getInstance().getPostService().addPostView(postId);
                session.setAttribute(POST, postId);
                List<PostComment> postComments;
                List<PostComment> currentPostComments;
                postComments = ServiceFactory.getInstance().getPostCommentService().getAllPostComments();

                currentPostComments = postComments.stream()
                        .filter(postComment -> postComment.getPost().getId().equals(postById.getId()))
                        .collect(Collectors.toList());

                int postCommentSize = currentPostComments.size();
                session.setAttribute(POST_COMMENT_SIZE, postCommentSize);
                session.setAttribute(POST_COMMENTS, currentPostComments);

                if (req.getSession().getAttribute(AUTHORIZE_USER) == null) {
                    session.setAttribute(TEXT, postById.getText());
                    req.getServletContext().getRequestDispatcher("/post.jsp").forward(req, resp);
                } else {
                    User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
                    switch (user.getRole().getRoleTypes().toString()) {
                        case MODERATOR -> {
                            session.setAttribute(TEXT, postById.getText());
                            req.getServletContext().getRequestDispatcher("/moderatorPost.jsp").forward(req, resp);
                        }
                        case USER -> {
                            session.setAttribute(TEXT, postById.getText());
                            req.getServletContext().getRequestDispatcher("/userPost.jsp").forward(req, resp);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void actionPostById(HttpServletRequest req, HttpServletResponse resp) {
        String postId = (String) req.getSession().getAttribute(POST);
        try {
            Post postById = ServiceFactory.getInstance().getPostService().getPostById(postId);
            if (req.getSession().getAttribute(AUTHORIZE_USER) == null) {
                String value = req.getParameter(VALUE);
                ServiceFactory.getInstance().getPostVoteService().setPostVote(postId, value);
                req.getServletContext().getRequestDispatcher("/post.jsp").forward(req, resp);
            } else if (req.getSession().getAttribute(AUTHORIZE_USER) != null) {
                User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
                switch (user.getRole().getRoleTypes().toString()) {
                    case USER -> {
                        String value = req.getParameter(VALUE);
                        String title = postById.getTitle();
                        String email = user.getEmail();
                        String textComment = req.getParameter(TEXT_COMMENT);
                        if (textComment == null) {
                            ServiceFactory.getInstance().getPostVoteService().setPostVote(postId, value);
                        }
                        if (value == null) {
                            ServiceFactory.getInstance().getPostCommentService().addPostComment(title, email, textComment);
                            req.setAttribute(ADD_COMMENT, "Add comment to post");
                        }
                        req.getServletContext().getRequestDispatcher("/userPost.jsp").forward(req, resp);
                    }
                    case MODERATOR -> {
                        String title = postById.getTitle();
                        String status = req.getParameter(STATUS);
                        ServiceFactory.getInstance().getUserService().setModeratorStatus(title, status);
                        req.getServletContext().getRequestDispatcher("/moderatorPost.jsp").forward(req, resp);
                    }
                }
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void getMyComments(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getSession().getAttribute(AUTHORIZE_USER) == null) {
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                User user = (User) req.getSession().getAttribute(AUTHORIZE_USER);
                List<PostComment> postComments;
                List<PostComment> myPostComments = new ArrayList<>();
                postComments = ServiceFactory.getInstance().getPostCommentService().getAllPostComments();
                for (PostComment postComment : postComments) {
                    if (user.getId().equals(postComment.getUser().getId())) {
                        myPostComments.add(postComment);
                    }
                }
                session.setAttribute(POST_COMMENT, myPostComments);
                req.getServletContext().getRequestDispatcher("/listMyPostComment.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public void actionMyComments(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User authoriseUser = (User) req.getSession().getAttribute(AUTHORIZE_USER);
        String action = req.getParameter(ACTION);
        String textComment = req.getParameter(TEXT_COMMENT);
        String email = req.getParameter(EMAIL);
        String newTextComment = req.getParameter(NEW_TEXT_COMMENT);
        String deleteTextComment = req.getParameter(DELETE_TEXT_COMMENT);
        String deleteEmail = req.getParameter(DELETE_EMAIL);
        try {
            switch (action) {
                case UPDATE_COMMENT: {
                    User user = ServiceFactory.getInstance().getUserService().findUserByEmail(email);
                    PostComment postComment = ServiceFactory.getInstance().getPostCommentService()
                            .getPostCommentByText(textComment);
                    if (postComment == null || !authoriseUser.getId().equals(postComment.getUser().getId())) {
                        session.setAttribute(UPDATE_TEXT_COMMENT, "Введен неверный комментарий");
                    } else if (user == null || !authoriseUser.getId().equals(user.getId())) {
                        session.setAttribute(UPDATE_TEXT_COMMENT, "Введен неверный адрес электронной почты");
                    } else {
                        session.setAttribute(UPDATE_TEXT_COMMENT, "Комментарий пользователя успешно обновлен");
                        ServiceFactory.getInstance().getPostCommentService()
                                .updatePostCommentByText(textComment, email, newTextComment);
                    }
                }
                case DELETE_COMMENT: {
                    User user = ServiceFactory.getInstance().getUserService().findUserByEmail(deleteEmail);
                    PostComment postComment = ServiceFactory.getInstance().getPostCommentService()
                            .getPostCommentByText(deleteTextComment);
                    if (postComment == null || !authoriseUser.getId().equals(postComment.getUser().getId())) {
                        session.setAttribute(DELETE_TEXT_COMMENT, "Введен неверный комментарий");

                    } else if (user == null || !authoriseUser.getId().equals(user.getId())) {
                        session.setAttribute(DELETE_TEXT_COMMENT, "Введен неверный адрес электронной почты");

                    } else {
                        session.setAttribute(DELETE_TEXT_COMMENT, "Комментарий пользователя успешно удален");
                        ServiceFactory.getInstance().getPostCommentService().deletePostComment(deleteTextComment, deleteEmail);
                    }
                }
            }
            req.getServletContext().getRequestDispatcher("/listMyPostComment.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new CommandProviderException(e);
        }
    }


    public static ListCommand getInstance() {
        return new ListCommand();
    }
}
