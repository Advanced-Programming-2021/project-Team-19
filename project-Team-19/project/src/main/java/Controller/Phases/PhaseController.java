package Controller.Phases;

import Controller.GameData;

public class PhaseController {
    private GameData gamedata;

    public PhaseController(GameData gamedata) {
        this.gamedata = gamedata;
    }

    public String run() {
        boolean gameIsOver = false;
        while (!gameIsOver) {
            new DrawPhase(gamedata).run();
            new StandbyPhase(gamedata).run();
            new MainPhase(gamedata, 1).run();
            new BattlePhase(gamedata).run();
            new MainPhase(gamedata, 2).run();
            new EndPhase(gamedata).run();
            gameIsOver = gamedata.isGameOver();
        }
        return "";
    }

}
