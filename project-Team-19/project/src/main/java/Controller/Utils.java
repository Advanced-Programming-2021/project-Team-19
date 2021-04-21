package Controller;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Matcher getMatcher(String matchingStr, String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(matchingStr);
    }

    public static String getFirstGroupInMatcher(Matcher matcher){
        matcher.find();
        return matcher.group(1);
    }

    public static <key, value>HashMap<key, value> getHashMap(Object ... keysAndValues){

        HashMap<key, value> hashMap = new HashMap<>();
        for(int i = 0; i < keysAndValues.length; i += 2){
            hashMap.put((key)keysAndValues[i], (value) keysAndValues[i + 1]);
        }
        return hashMap;
    }

    public static String getUsersPath(){
        return "Resource\\Users";
    }

    public static String getUserFileNameByUsername(String username) {
        return Utils.getUsersPath() + "\\" + username + ".json";
    }



}
