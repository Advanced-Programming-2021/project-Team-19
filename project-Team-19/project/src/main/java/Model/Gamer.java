package Model;

public class Gamer {
    AllBoards gameBoard = new AllBoards();
    private int lifePoint;
    User user;

    public Gamer(User user) {
        this.user = user;
    }

    public int getLifePoint(){
        return lifePoint;
    }

    public String toString(){
        return user.getNickname()+":"+getLifePoint();
    }
}
