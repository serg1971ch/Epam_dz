package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileServiceImpl implements FileService{

    @Override
    public String readTextFromFile(String filePath) {
        StringBuilder textFromFile = new StringBuilder();
        try(FileReader fileReader = new FileReader(filePath)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                textFromFile.append(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return textFromFile.toString();
    }
}
