package View;

public class Printer {

    public static void print(String string){
        System.out.println(string);
    }

    public static void printFormatError(String string){
        print(string + " format is not valid");
    }

    public static void printPasswordSafetyError(){
        print("password is week");
    }

    public static void printRepetitiousUsername(String username){
        print("user with username " + username + " already exists");
    }

    public static void printRepetitousNickName(String nickname){
        print("user with nickname " + nickname + " already exists");
    }

    public static void printSuccessfulRegister(){
        print("user created successfully!");
    }
}
