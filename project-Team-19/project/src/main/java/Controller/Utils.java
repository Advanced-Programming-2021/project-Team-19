package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Matcher getMatcher(String matchingStr, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(matchingStr);
    }

    public static String getUsersPath(){
        return "Resource\\Users";
    }
}
