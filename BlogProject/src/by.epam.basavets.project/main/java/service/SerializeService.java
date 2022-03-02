package service;

import bean.Moderator;
import dao.*;

import java.io.*;

public class SerializeService {

    private final ModeratorService moderatorService;
    private final UserService userService;
    private final ModeratorDAO moderatorDAO;
    private final UserDAO userDAO;

    public SerializeService(ModeratorService moderatorService, UserService userService, ModeratorDAO moderatorDAO, UserDAO userDAO) {

        this.moderatorService = moderatorService;
        this.userService = userService;
        this.moderatorDAO = moderatorDAO;
        this.userDAO = userDAO;
    }


//    DataSource dataSource;

//    public void writeFile(List <Moderator> moderators){
//        FileOutputStream fileOutputStream;
//        {
//            try {
//                fileOutputStream = new FileOutputStream("save.ser");
//                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//                for (Moderator moderator : moderators){
//                    objectOutputStream.writeObject(moderator + "\n");
//                }
//                objectOutputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void writeFile(ModeratorDAO moderatorDAO){
        FileOutputStream fileOutputStream;
        {
            try {
                fileOutputStream = new FileOutputStream("save.ser");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                for (Moderator moderator : moderatorDAO.getModerators()){
                    objectOutputStream.writeObject(moderator + "\n");
                }
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public void readFile(){

        FileInputStream fileInputStream;
        {
            try {
                fileInputStream = new FileInputStream("save.ser");
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    try {
                        for (Moderator moderator : moderatorDAO.getModerators()){
                            String otput = (String) objectInputStream.readObject();
                            System.out.println(otput + "ХУЙ");
                            String name = moderator.getName();
                            String password = moderator.getPassword();
                            String email = moderator.getEmail();
                            if (moderatorDAO.findModeratorByEmail(email) == null){
                                moderatorService.registerModerator(name, password, email);
                            }
                        }







//                          Moderator  moderator = (Moderator) objectInputStream.readObject();
//                          moderators.add(moderator);


//                            //System.out.println(moderator.getName());

                        //}
//                        ModeratorDAO moderatorDAO = (ModeratorDAO) objectInputStream.readObject();
//                        moderatorDAO.read();
//                        UserDAO userDAO = (UserDAO) objectInputStream.readObject();
//                        userDAO.read();
//                        PostDAO postDAO = (PostDAO) objectInputStream.readObject();
//                        PostCommentDAO postCommentDAO = (PostCommentDAO) objectInputStream.readObject();
//                        postCommentDAO.read();
//                        PostVoteDAO postVoteDAO = (PostVoteDAO) objectInputStream.readObject();
//                        SettingsDAO settingsDAO = (SettingsDAO) objectInputStream.readObject();
//                        System.out.println(moderatorDAO);
//                        System.out.println(userDAO);
//                        System.out.println(postDAO);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
