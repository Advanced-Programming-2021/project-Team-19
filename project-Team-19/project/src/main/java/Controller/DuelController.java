package Controller;

import java.util.regex.Matcher;

class DuelController {
    static DuelController instance=null;
    public static DuelController getInstance(){
        if(instance==null){
            instance=new DuelController();
        }
        return instance;
    }
    private void startDuel(Matcher matcher){

    }
    public void handleDuelMenu(){

    }

}
