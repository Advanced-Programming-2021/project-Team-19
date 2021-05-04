package Controller;

import Model.Gamer;

public class GameData {
    Gamer firstGamer;
    Gamer secondGamer;

    public GameData(Gamer firstGamer, Gamer secondGamer) {
        this.firstGamer = firstGamer;
        this.secondGamer = secondGamer;
    }

    public void SwapUsers() {
        Gamer temp = secondGamer;
        secondGamer = firstGamer;
        firstGamer = temp;
    }

    public boolean isGameOver() {
        return false;
    }
}
