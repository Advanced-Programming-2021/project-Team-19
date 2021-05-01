package Controller;

import Controller.DataBaseControllers.DeckDataBaseController;
import Controller.DataBaseControllers.UserDataBaseController;
import Model.Gamer;
import Model.User;
import View.GetInput;

import java.util.regex.Matcher;

class DuelController {
    static DuelController instance = null;

    User user;

    private boolean commandIsDone;

    public static DuelController getInstance() {
        if (instance == null) {
            instance = new DuelController();
        }
        return instance;
    }

    private void startDuel(Matcher matcher) {
        if(matcher.matches()){
            commandIsDone=true;
            User rival= UserDataBaseController.getUserByUsername(matcher.group(1));
            int turn=Integer.parseInt(matcher.group(2));
            if(rival==null){
                System.out.println("there is no player with this username");
            }
            else if(user.getActiveDeckName()==null){
                System.out.println(user.getUsername()+"has no active deck");
            }
            else if(rival.getActiveDeckName()==null){
                System.out.println(rival.getUsername()+"has no active deck");
            }
            else if(!DeckDataBaseController.getDeckByName(user.getUsername()+"_"+user.getActiveDeckName()).isDeckValid()){
                System.out.println(user.getUsername()+"’s deck is invalid");
            }
            else if(!DeckDataBaseController.getDeckByName(rival.getUsername()+"_"+rival.getActiveDeckName()).isDeckValid()){
                System.out.println(rival.getUsername()+"’s deck is invalid");
            }
            else if(turn!=1&&turn!=3){
                System.out.println("number of rounds is not supported");
            }
            else{
                Gamer firstGamer=new Gamer(user);
                Gamer secondGamer=new Gamer(rival);
//                new Game(firstGamer,secondGamer);
            }
        }
    }

    private void startDuelWithAi(Matcher matcher){
        if(matcher.find()){
            commandIsDone=true;
            int turn=Integer.parseInt(matcher.group(1));
            if(user.getActiveDeckName()==null){
                System.out.println(user.getUsername()+"has no active deck");
            }
            else if(!DeckDataBaseController.getDeckByName(user.getUsername()+"_"+user.getActiveDeckName()).isDeckValid()){
                System.out.println(user.getUsername()+"’s deck is invalid");
            }
            else if(turn!=1&&turn!=3){
                System.out.println("number of rounds is not supported");
            }
            else{

            }
        }
    }

    public void handleDuelMenu(User user) {
        this.user=user;
        String command= GetInput.getString();
        while(true){
            commandIsDone=false;
            startDuel(Utils.getMatcher(command,"duel --new --second-player (\\S) --rounds (\\d)"));
            startDuelWithAi(Utils.getMatcher(command,"duel --new --ai --rounds \\d"));
        }
    }

}
