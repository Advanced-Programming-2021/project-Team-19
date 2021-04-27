package View;

import java.util.regex.Matcher;

public class DataToSendToServer {
    public String message;
    public String username;
    public String menuName;

    public DataToSendToServer(String message, String username, String menuName) {
        this.message = message;
        this.username = username;
        this.menuName = menuName;
    }

    public DataToSendToServer(String message, String menuName) {
        this.message = message;
        this.username = null;
        this.menuName = menuName;
    }

}
