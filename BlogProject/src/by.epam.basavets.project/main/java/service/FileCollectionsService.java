package service;

import bean.Moderator;
import bean.User;
import dao.ModeratorDAO;
import dao.UserDAO;

import java.io.*;

public class FileCollectionsService {

    private final ModeratorService moderatorService;
    private final UserService userService;
    private final ModeratorDAO moderatorDAO;
    private final UserDAO userDAO;
    private final String MODERATOR_FILE = "save10.txt";
    private final String USER_FILE = "save11.txt";


    public FileCollectionsService(ModeratorService moderatorService, UserService userService,
                                  ModeratorDAO moderatorDAO, UserDAO userDAO) {
        this.moderatorService = moderatorService;
        this.userService = userService;
        this.moderatorDAO = moderatorDAO;
        this.userDAO = userDAO;
    }


    public void writeModerator(ModeratorDAO moderatorDAO) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(MODERATOR_FILE);
        for (Moderator moderator : moderatorDAO.getModerators()) {
            writer.write(moderator.getId() + "/" + moderator.getName() + "/" + moderator.getPassword() + "/" +
                    moderator.getEmail() + "/" + moderator.getRegTime() + "/" + moderator.getPosts() +
                    "/" + moderator.getPostComments() + "\n");
        }
        writer.flush();
        writer.close();
    }


    public void writeUser(UserDAO userDAO) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(USER_FILE);
        for (User user : userDAO.getUsers()) {
            writer.write(user.getId() + "/" + user.getName() + "/" + user.getPassword() + "/" +
                    user.getEmail() + "/" + user.getRegTime() + "/" + user.getPosts() +
                    "/" + user.getPostComments() + "\n");
        }
        writer.flush();
        writer.close();
    }


    public void readModerators() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(MODERATOR_FILE));
        String name = "";
        String password = "";
        String email = "";
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            String[] out = line.split("/");
            for (int i = 0; i < out.length; i++) {
                name = out[1];
                password = out[2];
                email = out[3];
            }
            if (moderatorDAO.findModeratorByEmail(email) == null) {
                moderatorService.registerModerator(name, password, email);
            }
        }
    }


    public void readUsers() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(USER_FILE));
        String name = "";
        String password = "";
        String email = "";
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            String[] out = line.split("/");
            for (int i = 0; i < out.length; i++) {
                name = out[1];
                password = out[2];
                email = out[3];
            }
            if (userDAO.findUserByEmail(email) == null) {
                userService.registerUser(name, password, email);
            }
        }
    }
}
