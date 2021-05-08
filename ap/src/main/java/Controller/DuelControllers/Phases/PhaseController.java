package Controller.DuelControllers.Phases;

import Controller.DuelControllers.GameData;

public class PhaseController extends AllPhases {

    public PhaseController(GameData gameData) {
        super.gameData = gameData;
    }

    public String run() {
        boolean gameIsOver = false;
        while (!gameIsOver) {
            if (gamerCanDraw()) {
                new DrawPhase(gameData).run();
            }
            new StandbyPhase(gameData).run();
            new MainPhase(gameData, 1).run();
            new BattlePhase(gameData).run();
            new MainPhase(gameData, 2).run();
            new EndPhase(gameData).run();
            gameData.turnFinished();
            gameIsOver = gameData.isGameOver();
        }
        return "";
    }

    private boolean gamerCanDraw() {
        return gameData.getFirstGamer().getGameBoard().deckZone.getSize() != 0;
    }

}
