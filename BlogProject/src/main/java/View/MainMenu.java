package View;

import Controller.DefaultController;

import java.util.Scanner;

public class MainMenu {

    Scanner scanner = new Scanner(System.in);
    private final DefaultController defaultController;

    public MainMenu(DefaultController defaultController) {
        this.defaultController = defaultController;
    }

    public void run() {
        try {
            System.out.println("Список доступных команд для незарегистрированного пользователя:");

            System.out.println("Регистрация модератора: нажмите 1");
            System.out.println("Просмотр аккаунта модератора: нажмите 2");
            System.out.println("Авторизация модератора: нажмите 3");
            System.out.println("Регистрация пользователя: нажмите 4");
            System.out.println("Просмотр всех пользователей: нажмите 5");
            System.out.println("Просмотр всех постов: нажмите 6");
            System.out.println("Проголосовать за пост: нажмите 7");
            System.out.println("Авторизация: нажмите 8");
            System.out.println("Просмотр комментариев к постам: нажмите 9");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    defaultController.getModeratorService().registerModerator();
                    break;
                case "2":
                    defaultController.getModeratorService().getAllModerators();
                    break;
                case "3":
                    defaultController.getModeratorService().authorithationModerator();
                    break;
                case "4":
                    defaultController.getUserService().registerUser();
                    break;
                case "5":
                    defaultController.getUserService().getAllUsers();
                    break;
                case "6":
                    defaultController.getPostService().getAllPosts();
                    break;
                case "7":
                    defaultController.getPostVoteService().setPostVote();
                    break;
                case "8":
                    defaultController.getUserService().authorithationUser();
                    break;
                case "9":
                    defaultController.getPostCommentService().getAllPostComments();

                default:
                    System.out.println("Неверная команда ввода: " + input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
