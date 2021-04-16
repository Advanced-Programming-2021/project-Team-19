package Controller;

import java.util.regex.Matcher;

class ProfileMenuController {
    static ProfileMenuController instance=null;
    public static ProfileMenuController getInstance(){
        if(instance==null){
            instance =new ProfileMenuController();
        }
        return instance;
    }
    private void changeNickName(Matcher matcher){

    }
    private void changePassword(Matcher matcher){

    }
    public void manageProfileMenu(){

    }
}
