package Service;

import Bean.Enum.SettingStatus;
import Bean.Settings;
import DAO.ModeratorDAO;
import DAO.PostDAO;
import DAO.SettingsDAO;

import java.util.Scanner;

public class SettingsService {


    Settings settings = new Settings();

    private final SettingsDAO settingsDAO;
    //private final ModeratorDAO moderatorDAO;
    //private final PostDAO postDAO;
    Scanner scanner = new Scanner(System.in);

    public SettingsService(SettingsDAO settingsDAO) {
        this.settingsDAO = settingsDAO;
        //this.moderatorDAO = moderatorDAO;
        //this.postDAO = postDAO;
    }


    public void setSettings() {
        //ModeratorService moderatorService = new ModeratorService(moderatorDAO, postDAO);
        //if (moderatorService.authorithationModerator()){
            System.out.println("Разрешить или запретить доступ");
            String input = scanner.nextLine();
            if (input.equals("Разрешить")) {
                settings.setSettingStatus(SettingStatus.Yes);
                settingsDAO.addSettings(settings);
                System.out.println("Доступ разрешен");
            }
            if (input.equals("Запретить")) {
                settings.setSettingStatus(SettingStatus.No);
                settingsDAO.addSettings(settings);
                System.out.println("Доступ запрещен");
            }
        //}
    }

    public void showSettings() {
        if (settings.getSettingStatus() == null) {
            settings.setSettingStatus(SettingStatus.Yes);
            settingsDAO.addSettings(settings);
        }
        System.out.println("Статус добавления постов - " + settings.getSettingStatus());
    }
}
