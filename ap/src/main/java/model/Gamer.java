package model;

public class Gamer {


    AllBoards gameBoard ;
    private int lifePoint = 4000;
    private User user;
    private Deck deck;
    private int lastTurnHasSummonedOrSet = 0;

    public Gamer(User user) {
        this.user = user;
        gameBoard=new AllBoards(user);
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public int getLastTurnHasSummonedOrSet(){
        return lastTurnHasSummonedOrSet;
    }

    public void setLastTurnHasSummoned(int lastTurnHasSummoned){
        this.lastTurnHasSummonedOrSet = lastTurnHasSummoned;
    }

    public String getBoardForSelf() {
        return gameBoard.selfToString() +
                user.getNickname() + ":" + lifePoint;
    }

    public String getBoardForRival() {
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

    public String getUsername() {
        return this.user.getUsername();
    }
}
