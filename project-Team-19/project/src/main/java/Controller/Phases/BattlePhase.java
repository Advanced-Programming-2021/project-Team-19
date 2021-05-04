package Controller.Phases;

import Controller.GameData;
import Model.Card.Monster;

public class BattlePhase {
    private GameData gamedata;

    public BattlePhase(GameData gamedata) {
        this.gamedata = gamedata;
    }

    public String run() {
        return "";
    }

    public String AttackMonster(Monster AttackingMonster, Monster DefendingMonster) {
        return "";
    }

    public String AttackPlayer(Monster AttackingMonster) {
        return "";
    }
}
