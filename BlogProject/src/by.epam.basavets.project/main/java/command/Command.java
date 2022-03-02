package command;

import dao.*;
import service.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class Command implements Serializable{

    private static final long serialVersionUID = 11L;

    UserDAO userDAO = new UserDAO();
    PostDAO postDAO = new PostDAO();
    ModeratorDAO moderatorDAO = new ModeratorDAO();
    PostCommentDAO postCommentDAO = new PostCommentDAO();
    PostVoteDAO postVoteDAO = new PostVoteDAO();
    SettingsDAO settingsDAO = new SettingsDAO();
    DataSource dataSource = new DataSource(moderatorDAO, postVoteDAO, postCommentDAO, postDAO, settingsDAO, userDAO);

    UserService userService = new UserService(dataSource);
    ModeratorService moderatorService = new ModeratorService(dataSource);
    PostServise postService = new PostServise(dataSource);
    PostCommentServise postCommentService = new PostCommentServise(dataSource);
    PostVoteServise postVoteService = new PostVoteServise(dataSource);
    SettingsService settingsService = new SettingsService(dataSource);
    SerializeService serializeService = new SerializeService(moderatorService, userService, moderatorDAO, userDAO);
    ServiceFileCollections serviceFileCollections = new ServiceFileCollections(moderatorService, userService,
            moderatorDAO, userDAO);



    public UserService getUserService() {
        return userService;
    }

    public ModeratorService getModeratorService() {
        return moderatorService;
    }

    public PostServise getPostService() {
        return postService;
    }

    public PostCommentServise getPostCommentService() {
        return postCommentService;
    }

    public PostVoteServise getPostVoteService() {
        return postVoteService;
    }

    public SettingsService getSettingsService() {
        return settingsService;
    }


    public void runWriteFile(){
        serializeService.writeFile(moderatorDAO);
    }






    public void readFromFile(){
        serializeService.readFile();
    }



    public void writeCollections() throws FileNotFoundException {
        serviceFileCollections.writeModerator(moderatorDAO);
        serviceFileCollections.writeUser(userDAO);
   }

    public void readCollections() throws IOException {
        serviceFileCollections.readModerators();
        serviceFileCollections.readUsers();
    }
}










