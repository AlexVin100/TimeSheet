package timesheet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import net.sf.json.JSONArray;

public class JsonFileWriter {
    public static void writeJsonFile(String filePath, JSONArray contents) throws FileNotFoundException, IOException {
        
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(contents.toString(4));
            writer.flush();
        }
        
    }
}
