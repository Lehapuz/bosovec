package by.epam.basavets.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {

    private final Logger logger = LogManager.getRootLogger();

    public int getShipCount(String path) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            logger.error("Файл не найден");
        }
        while (true) {
            String line = null;
            if (bufferedReader != null) {
                line = bufferedReader.readLine();
            }
            if (line == null) {
                break;
            } else {
                builder.append(line).append("\n");
            }
        }
        return Integer.parseInt(builder.toString().replaceAll("[^0-9]", ""));
    }
}
