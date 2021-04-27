package Controller;

import Controller.DataBaseControllers.UserDataBaseController;
import Model.User;
import View.GetInput;
import View.Printer.Printer;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.regex.Matcher;

public class ScoreBoardMenuController extends MenuController{

    static ScoreBoardMenuController instance = null;

    private boolean commandIsDone;

    private ScoreBoardMenuController() {
        super("Scoreboard Menu");
    }

    static ScoreBoardMenuController getInstance() {

        if (instance == null) {
            instance = new ScoreBoardMenuController();
        }
        return instance;
    }

    public void run() {
        String command= GetInput.getString();
        while(!command.equals("menu exit")){
            commandIsDone=false;
            showScores(Utils.getMatcher(command,"scoreboard show"));
            changeMenu(command);
            if(!commandIsDone){
                Printer.print("Hello");
            }
            command=GetInput.getString();
        }
    }

    private TreeSet<User> allUsers = new TreeSet<>(new UserComp());

    private void showScores(Matcher showScoresMatcher) {
        if(showScoresMatcher.matches()){
            commandIsDone=true;
            gatherAllUsers();
            int rank=0;
            int currentScore=-10;
            for(User user:allUsers){
                if(user.getScore()!=currentScore){
                    currentScore=user.getScore();
                    rank++;
                }
                Printer.print(rank+"-"+user.toString());
            }
        }
    }

    private void gatherAllUsers(){
        allUsers.clear();
        allUsers.addAll(UserDataBaseController.allUsers());
    }

    private void changeMenu(String command){
        if(command.startsWith("menu ")){
            commandIsDone=true;
            handleMenuOrders(command);
        }
    }

}


class UserComp implements Comparator<User> {

    public int compare(User firstUser, User secondUser) {
        int scoreComp=Integer.compare(firstUser.getScore(),secondUser.getScore());
        int nickNameComp=firstUser.getUsername().compareTo(secondUser.getUsername());
        if(scoreComp==0){
            return nickNameComp;
        }
        else{
            return -scoreComp;
        }
    }

}