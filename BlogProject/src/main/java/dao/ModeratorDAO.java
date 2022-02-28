package dao;

import bean.Moderator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ModeratorDAO {

    List<Moderator> moderators = new ArrayList<>();
    private final Logger logger = LogManager.getRootLogger();

    public void addModerator(Moderator moderator) {
        moderators.add(moderator);
        logger.info("Модератор - " + moderator.getName() + " успешно зарегистрирован");
    }

    public void read() {
        if (moderators.size() == 0) {
            logger.info("Модераторы отсутствуют");
        }
        for (Moderator moderator : moderators) {
            logger.info(moderator.toString());
        }
    }

    public void updateModerator(Moderator moderator, Moderator newModerator) {
        moderators.removeIf(moderator1 -> moderator1.equals(moderator));
        moderators.add(newModerator);
        logger.info("Аккаунт модератора - " + moderator.getName() + " успешно изменен на аккаунт модератора - "
                + newModerator.getName());
    }

    public void deleteModerator(Moderator moderator) {
        moderators.removeIf(moderator1 -> moderator1.equals(moderator));
        logger.info("Аккаунт модератора - " + moderator.getName() + " успешно удален");
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
