package Service;

import Bean.Enum.ModeratorStatus;
import Bean.Moderator;
import Bean.Post;
import DAO.ModeratorDAO;
import DAO.PostDAO;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ModeratorService {


    private final ModeratorDAO moderatorDAO;
    private final PostDAO postDAO;
    Scanner scanner = new Scanner(System.in);
    private boolean isRegistration;

    public ModeratorService(ModeratorDAO moderatorDAO, PostDAO postDAO) {
        this.moderatorDAO = moderatorDAO;
        this.postDAO = postDAO;
    }


    public void registerModerator() {
        Moderator moderator = new Moderator();
        System.out.println("Введите имя");
        String input = scanner.nextLine();
        int i = 0;
        moderator.setName(input);
        System.out.println("Имя добавлено" + "\n" + "Введите пароль");
        input = scanner.nextLine();
        moderator.setPassword(input);
        System.out.println("пароль добавлен" + "\n" + "Введите адресс электронной почты");
        input = scanner.nextLine();
        moderator.setEmail(input);
        moderator.setRegTime(LocalDateTime.now());
        moderator.setId(++i);
        if (isValidEmail(moderator.getEmail()) && moderatorDAO.getModerators().isEmpty()) {
            moderatorDAO.addModerator(moderator);
        }
        if (isValidEmail(moderator.getEmail()) && !moderatorDAO.getModerators().isEmpty() && !moderator.getEmail()
                .equals(moderatorDAO.findModeratorByEmail(moderator.getEmail()).getEmail())) {
            moderatorDAO.addModerator(moderator);
        } else {
            System.out.println("Неверный формат ввода или " +
                    "такой адрес электронной почты уже зарегистрирован");
        }
    }


    public void getAllModerators() {
        moderatorDAO.read();
    }


    public void deleteModeratorByEmail() {
        try {
            System.out.println("Введите адресс электронной почты");
            String input = scanner.nextLine();
            if (input.equals(moderatorDAO.findModeratorByEmail(input).getEmail())) {
                Moderator moderator = moderatorDAO.findModeratorByEmail(input);
                System.out.println("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(moderator.getPassword())) {
                    moderatorDAO.deleteModerator(moderator);
                    isRegistration = false;
                } else {
                    System.out.println("Адрес электронной почты не верный");
                }
            } else {
                System.out.println("Пароль неверный");
            }
        } catch (Exception e) {
            System.out.println("Модератор не найден");
        }
    }


    public void updateModeratorByEmail() {
        try {
            System.out.println("Введите адресс электронной почты");
            String input = scanner.nextLine();
            Moderator moderator;
            Moderator moderator1 = new Moderator();
            if (input.equals(moderatorDAO.findModeratorByEmail(input).getEmail())) {
                moderator = moderatorDAO.findModeratorByEmail(input);
                System.out.println("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(moderator.getPassword())) {
                    moderator1.setEmail(moderator.getEmail());
                    moderator1.setId(moderator.getId());
                    moderatorDAO.updateModerator(moderator, moderator1);
                    System.out.println("Введите новое имя");
                    input = scanner.nextLine();
                    moderator1.setName(input);
                    System.out.println("Имя обновлено" + "\n" + "Введите новый пароль");
                    input = scanner.nextLine();
                    moderator1.setPassword(input);
                    System.out.println("пароль обновлен");
                    moderator1.setRegTime(LocalDateTime.now());
                } else {
                    System.out.println("Адрес электронной почты не верный");
                }
            } else {
                System.out.println("Пароль неверный");
            }
        } catch (Exception e) {
            System.out.println("Модератор не найден");
        }
    }


    public void authorithationModerator() {
        try {
            System.out.println("Введите адресс электронной почты");
            String input = scanner.nextLine();
            if (input.equals(moderatorDAO.findModeratorByEmail(input).getEmail())) {
                Moderator moderator = moderatorDAO.findModeratorByEmail(input);
                System.out.println("Введите пароль");
                input = scanner.nextLine();
                if (input.equals(moderator.getPassword())) {
                    System.out.println("Авторизация пользователя прошла успешно");
                }
                System.out.println(moderator.toString());
                isRegistration = true;
            } else {
                System.out.println("Авторизация не прошла");
            }
        } catch (Exception e) {
            System.out.println("Такого пользователя не существует");
        }
    }


    public void setModeratorStatus() {
        try {
            System.out.println("Введите название поста");
            String input = scanner.nextLine();
            Post post;
            if (input.equals(postDAO.findPostByTitle(input).getTitle())) {
                post = postDAO.findPostByTitle(input);
                System.out.println("Задайте статус посту");
                input = scanner.nextLine();
                if (input.equals("OK")) {
                    post.setModeratorStatus(ModeratorStatus.ACCEPTED);
                    System.out.println("Статус изменен");
                }
                if (input.equals("NO")) {
                    post.setModeratorStatus(ModeratorStatus.DECLINED);
                    System.out.println("Статус изменен");
                }
            }
        } catch (Exception e) {
            System.out.println("Пост не найден");
        }
    }


    public void exit() {
        isRegistration = false;
    }


    public boolean getRegistration() {
        return isRegistration;
    }


    private boolean isValidEmail(String email) {
        return email.matches("\\w+@\\w+\\.\\w+");
    }
}
