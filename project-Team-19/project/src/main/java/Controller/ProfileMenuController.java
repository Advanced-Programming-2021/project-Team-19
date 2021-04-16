package Controller;

import Model.User;

import java.util.regex.Matcher;

class ProfileMenuController {

    private static User user;

    private static ProfileMenuController instance = null;

    private ProfileMenuController(){}

    public static ProfileMenuController getInstance(User user){

        setUser(user);
        if(instance==null){
            instance =new ProfileMenuController();
        }
        return instance;

    }

    public void run(){}

    private static void setUser(User user){}


    private void changeNickName(Matcher matcher){}

    private void changePassword(Matcher matcher){}

    public void manageProfileMenu(){
    }
}
