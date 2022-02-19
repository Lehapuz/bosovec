package View;

import Controller.DefaultController;

import java.util.Scanner;

public class ModeratorMenu {

    Scanner scanner = new Scanner(System.in);
    private final DefaultController defaultController;

    public ModeratorMenu(DefaultController defaultController) {
        this.defaultController = defaultController;
    }

    public void run() {
        try {
                System.out.println("Список доступных команд для Модератора:");

                System.out.println("Смена аккаунта модератора: нажмите 1");
                System.out.println("Удаление аккаунт модератора: нажмите 2");
                System.out.println("Установка настройки для сайта: нажмите 3");
                System.out.println("Модерировать новый пост: нажмите 4");
                System.out.println("Просмотр всех постов: нажмите 5");
                System.out.println("Просмотр комментариев к постам: нажмите 6");
                System.out.println("Показать настройки: нажмите 7");
                System.out.println("Выход: нажмите 8");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        defaultController.getModeratorService().updateModeratorByEmail();
                        break;
                    case "2":
                        defaultController.getModeratorService().deleteModeratorByEmail();
                        break;
                    case "3":
                        defaultController.getSettingsService().setSettings();
                        break;
                    case "4":
                        defaultController.getModeratorService().setModeratorStatus();
                        break;
                    case "5":
                        defaultController.getPostService().getAllPosts();
                        break;
                    case "6":
                        defaultController.getPostCommentService().getAllPostComments();
                        break;
                    case "7":
                        defaultController.getSettingsService().showSettings();
                        break;
                    case "8":
                        defaultController.getModeratorService().exit();
                        break;
                    default:
                        System.out.println("Неверная команда ввода: " + input);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
