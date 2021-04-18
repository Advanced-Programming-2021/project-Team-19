package Controller;

import Model.User;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.regex.Matcher;

public class ScoreBoardMenuController {

    static ScoreBoardMenuController instance = null;

    private ScoreBoardMenuController(){}

    static ScoreBoardMenuController getInstance(){

        if(instance==null){
            instance =new ScoreBoardMenuController();
        }
        return instance ;
    }

    public void run(){}

    private TreeSet<User> allUsers = new TreeSet<>(new UserComp());

    private void showScores(Matcher showScoresMatcher){

    }

    public void addUser(User user){

    }

    public void removeUser(User user){

    }

}


class UserComp implements Comparator<User>{

    public int compare(User firstUser,User secondUser){
        return 1;
    }

}