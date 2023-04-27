package qu.actions;

import org.json.JSONObject;
import qu.pages.loginPages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonActions {

    public static String getStringRandom(int size){
        return loginPages.getAlphabetRandom(size);
    }

    public static JSONObject getJsonData(String path){
        String contentFile = "";
        try {
            contentFile = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject(contentFile);
    }
}
