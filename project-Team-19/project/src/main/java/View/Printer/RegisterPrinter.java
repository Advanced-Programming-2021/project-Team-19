package View.Printer;

public class RegisterPrinter extends Printer {

    public static void printFormatError(String string){
        print(string + " format is not valid");
    }

    public static void printPasswordSafetyError(){
        print("password is weak");
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