package Controller;

import Model.User;
import View.GetInput;

import java.util.regex.Matcher;

class ShopMenuController {

    User user;

    static ShopMenuController instance=null;

    public static ShopMenuController getInstance(){

        if(instance==null){
            instance=new ShopMenuController();
        }
        return instance;
    }

    public void run(User user){}

    private void buyGoods(Matcher matcher){

    }

    private void showAllCards(Matcher matcher){

    }

}
