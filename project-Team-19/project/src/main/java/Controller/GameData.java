package Controller;

import Model.Gamer;

import java.util.ArrayList;

public class GameData {
    ArrayList<Gamer> gamers = new ArrayList<>();

    public GameData(Gamer firstGamer, Gamer secondGamer) {
        gamers.add(firstGamer);
        gamers.add(secondGamer);
    }

    public boolean isGameOver() {
        return false;
    }
}
