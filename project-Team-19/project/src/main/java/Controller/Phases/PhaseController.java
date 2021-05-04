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
            new MainPhase1(gamedata).run();
            new BattlePhase(gamedata).run();
            new MainPhase2(gamedata).run();
            gamedata.SwapUsers();
            gameIsOver = gamedata.isGameOver();
        }
        return "";
    }

}
