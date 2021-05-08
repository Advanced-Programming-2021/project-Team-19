package Model;

import Model.Board.MonsterCardZone;

public class Gamer {
    AllBoards gameBoard ;
    private int lifePoint = 4000;
    private User user;
    private Deck deck;

    public Gamer(User user) {
        this.user = user;
        gameBoard=new AllBoards(user);
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public String selfToString() {
        return gameBoard.selfToString() +
                user.getNickname() + ":" + lifePoint;
    }

    public String rivalToString() {
        return user.getNickname() + ":" + lifePoint + "\n" +
                gameBoard.rivalToString();
    }

    public void increaseLifePoint(int amount) {
        this.lifePoint += amount;
    }

    public void decreaseLifePoint(int amount) {
        this.lifePoint -= amount;
        if (this.lifePoint < 0)
            this.lifePoint = 0;
    }

    public AllBoards getGameBoard(){
        return gameBoard;
    }
}
