package by.epam.basavets.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandProvider {

    private final String COMMAND = "command";
    private String command;
    private final String CHANGE_LANGUAGE = "change_language";
    private final String AUTHORIZE_USER = "authorize_user";
    private final String ALL_POSTS = "all_posts";
    private final String LOG_IN = "log_in";
    private final String DELETE_USER_ACTION = "delete_user_action";
    private final String UPDATE_USER_ACTION = "update_user_action";
    private final String EXIT_USER = "exit_user";
    private final String SET_LANGUAGE = "set_language";
    private final String MY_POST = "my_post";
    private final String MY_COMMENT = "my_comment";
    private final String POST_ID = "post_id";
    private final String UPDATE_USER = "update_user";
    private final String DELETE_USER = "delete_user";
    private final String ALL_MY_POSTS = "all_my_posts";
    private final String ALL_MY_COMMENTS = "all_my-comments";
    private final String EXIT_USER_PREPARE = "exit_user_prepare";
    private final String GET_SETTING = "get_setting";
    private final String SET_SETTING = "set_setting";
    private final String ACTION_POST_ID = "action_post_id";

    public void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        command = req.getParameter(COMMAND);
        try {
            if (command == null) {
                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                switch (command) {
                    case CHANGE_LANGUAGE -> ListCommand.getInstance().changeLanguageGet(req, resp);
                    case AUTHORIZE_USER -> ListCommand.getInstance().getUser(req, resp);
                    case ALL_POSTS -> ListCommand.getInstance().getPosts(req, resp);
                    case ALL_MY_POSTS -> ListCommand.getInstance().getMyPosts(req, resp);
                    case LOG_IN -> ListCommand.getInstance().actionUser(req, resp);
                    case SET_LANGUAGE -> ListCommand.getInstance().changeLanguage(req, resp);
                    case MY_POST -> ListCommand.getInstance().actionMyPosts(req, resp);
                    case EXIT_USER_PREPARE -> ListCommand.getInstance().getExitPage(req, resp);
                    case EXIT_USER -> ListCommand.getInstance().exitUser(req, resp);
                    case GET_SETTING -> ListCommand.getInstance().getSettings(req, resp);
                    case SET_SETTING -> ListCommand.getInstance().setSettings(req, resp);
                    case POST_ID -> ListCommand.getInstance().getPostById(req, resp);
                    case ACTION_POST_ID -> ListCommand.getInstance().actionPostById(req, resp);
                    case ALL_MY_COMMENTS -> ListCommand.getInstance().getMyComments(req, resp);
                    case MY_COMMENT -> ListCommand.getInstance().actionMyComments(req, resp);
                    case UPDATE_USER -> ListCommand.getInstance().getUpdateUserPage(req, resp);
                    case UPDATE_USER_ACTION -> ListCommand.getInstance().updateUser(req, resp);
                    case DELETE_USER -> ListCommand.getInstance().getDeleteUserPage(req, resp);
                    case DELETE_USER_ACTION -> ListCommand.getInstance().deleteUser(req, resp);
                }
            }
        } catch (Exception e) {
            try {
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            } catch (Exception servletException) {
                throw new CommandProviderException(servletException);
            }
        }
    }

    public static CommandProvider getInstance() {
        return new CommandProvider();
    }
}
