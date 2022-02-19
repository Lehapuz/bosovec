package View;

import Controller.DefaultController;

import java.util.Scanner;

public class UserMenu {

    Scanner scanner = new Scanner(System.in);
    private final DefaultController defaultController;

    public UserMenu(DefaultController defaultController) {
        this.defaultController = defaultController;
    }

    public void run() {
        try {
            System.out.println("Список доступных команд для зарегистрированного пользователя:");

            System.out.println("Обновление аккаунта пользователя: нажмите 1");
            System.out.println("Удаление аккаунта пользователя: нажмите 2");
            System.out.println("Добавление поста: нажмите 3");
            System.out.println("Просмотр всех постов: нажмите 4");
            System.out.println("Удалить пост: нажмите 5");
            System.out.println("Обновить пост: нажмите 6");
            System.out.println("Добавить комментарий: нажмите 7");
            System.out.println("Просмотр комментариев к постам: нажмите 8");
            System.out.println("Удалить комментарий: нажмите 9");
            System.out.println("Обновить комментарий: нажмите 10");
            System.out.println("Выход: нажмите 11");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    defaultController.getUserService().updateUserByEmail();
                    break;
                case "2":
                    defaultController.getUserService().deleteUserByEmail();
                    break;
                case "3":
                    defaultController.getPostService().addNewPost();
                    break;
                case "4":
                    defaultController.getPostService().getAllPosts();
                    break;
                case "5":
                    defaultController.getPostService().deletePostByTitle();
                    break;
                case "6":
                    defaultController.getPostService().updatePostByTitle();
                    break;
                case "7":
                    defaultController.getPostCommentService().addPostComment();
                    break;
                case "8":
                    defaultController.getPostCommentService().getAllPostComments();
                    break;
                case "9":
                    defaultController.getPostCommentService().deletePostComment();
                    break;
                case "10":
                    defaultController.getPostCommentService().updatePostCommentByText();
                    break;
                case "11":
                    defaultController.getUserService().exit();
                    break;
                default:
                    System.out.println("Неверная команда ввода: " + input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
