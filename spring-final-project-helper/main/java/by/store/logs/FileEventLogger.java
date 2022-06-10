package by.store.logs;

import by.store.Constants;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class FileEventLogger implements EventLogger {

    private File file;

    private String filename;

    public FileEventLogger() {
        this.filename = Constants.LOG_TXT;
    }

    @PostConstruct
    public void init() throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    @Override
    public void logEvent(String msg) {
        try {
            FileUtils.writeStringToFile(file, msg + "\n", true);
        } catch (IOException e) {
        }
    }
}
