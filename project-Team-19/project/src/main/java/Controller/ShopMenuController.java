package Controller;

import Model.User;
import View.GetInput;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ShopMenuController extends MenuController{

    User user;

    private boolean commandIsDone=false;
    private ShopMenuController(){
        super("Shop Menu", 2);
    }

    static ShopMenuController instance=null;

    public static ShopMenuController getInstance(){

        if(instance==null){
            instance=new ShopMenuController();
        }
        return instance;
    }

    private Matcher getMatcher(String regex,String command){
        Pattern commandPattern=Pattern.compile(regex);
        return commandPattern.matcher(command);
    }

    public void run(User user){
        this.user=user;
        while(true){
            String command=GetInput.getString();
            buyGoods(getMatcher("shop buy \\S+",command));
            showAllCards(getMatcher("shop show --all",command));
        }
    }

    private void buyGoods(Matcher matcher){
        if(matcher.matches()){
            commandIsDone=true;
        }
    }

    private void showAllCards(Matcher matcher){
        if(matcher.matches()){
            commandIsDone=true;
        }
    }

    private void changeMenu(Matcher matcher){

    }

}
