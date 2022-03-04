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
    PostService postService = new PostService(dataSource);
    PostCommentService postCommentService = new PostCommentService(dataSource);
    PostVoteService postVoteService = new PostVoteService(dataSource);
    SettingsService settingsService = new SettingsService(dataSource);
    SerializeService serializeService = new SerializeService(dataSource);
    FileCollectionsService fileCollectionsService = new FileCollectionsService(moderatorService, userService,
            moderatorDAO, userDAO);


    public UserService getUserService() {
        return userService;
    }

    public ModeratorService getModeratorService() {
        return moderatorService;
    }

    public PostService getPostService() {
        return postService;
    }

    public PostCommentService getPostCommentService() {
        return postCommentService;
    }

    public PostVoteService getPostVoteService() {
        return postVoteService;
    }

    public SettingsService getSettingsService() {
        return settingsService;
    }


    public void runWriteFile() throws IOException {
        serializeService.writeFile();
    }


    public void readFromFile() throws IOException, ClassNotFoundException {
        serializeService.readFile();
    }


    public void writeCollections() throws FileNotFoundException {
        fileCollectionsService.writeModerator(moderatorDAO);
        fileCollectionsService.writeUser(userDAO);
   }


    public void readCollections() throws IOException {
        fileCollectionsService.readModerators();
        fileCollectionsService.readUsers();
    }
}










