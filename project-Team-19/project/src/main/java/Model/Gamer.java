package Model;

import Controller.DataBaseControllers.DeckDataBaseController;

public class Gamer {
    AllBoards gameBoard = new AllBoards();
    private int lifePoint;
    private User user;
    private Deck deck;

    public Gamer(User user){
        this.user = user;
        //this.deck= (Deck) DeckDataBaseController.getDeckByName(user.getActiveDeckName()).clone();
    }

    public int getLifePoint(){
        return lifePoint;
    }

    public String selfToString(){
        return gameBoard.selfToString()+
                user.getNickname()+":"+lifePoint;
    }
    public String rivalToString(){
        return user.getNickname()+":"+lifePoint+"\n"+
                gameBoard.rivalToString();
    }
}
