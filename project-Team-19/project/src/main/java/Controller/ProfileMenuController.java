package Controller;

import Model.User;

import java.util.regex.Matcher;

class ProfileMenuController {

    private User user;

    private static ProfileMenuController instance = null;

    private ProfileMenuController(){}

    public static ProfileMenuController getInstance(){

        if(instance==null){
            instance =new ProfileMenuController();
        }
        return instance;

    }

    public void run(User user){setUser(user);}

    private  void setUser(User user){}


    private void changeNickName(Matcher matcher){}

    private void changePassword(Matcher matcher){}

    public void manageProfileMenu(){
    }
}
