package DAO;

import Bean.Moderator;

import java.util.ArrayList;
import java.util.List;

public class ModeratorDAO {

    List<Moderator> moderators = new ArrayList<>();

    public void addModerator(Moderator moderator) {
        moderators.add(moderator);
        System.out.println("Модератор - " + moderator.getName() + " успешно зарегистрирован");
    }

    public void read() {
        if (moderators.size() == 0) {
            System.out.println("Модераторы отсутствуют");
        }
        for (Moderator moderator : moderators) {
            System.out.println(moderator.toString());
        }
    }

    public void updateModerator(Moderator moderator, Moderator newModerator) {
        moderators.removeIf(moderator1 -> moderator1.equals(moderator));
        moderators.add(newModerator);
        System.out.println("Аккаунт модератора - " + moderator.getName() + " успешно изменен на аккаунт модератора - "
                + newModerator.getName());
    }

    public void deleteModerator(Moderator moderator) {
        moderators.removeIf(moderator1 -> moderator1.equals(moderator));
        System.out.println("Аккаунт модератора - " + moderator.getName() + " успешно удален");
    }

    public Moderator findModeratorByEmail(String email) {
        for (Moderator moderator : moderators) {
            if (moderator.getEmail().equals(email))
                return moderator;
        }
        return null;
    }

    public List<Moderator> getModerators() {
        return moderators;
    }
}
