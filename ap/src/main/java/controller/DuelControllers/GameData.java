package controller.DuelControllers;

import controller.DuelControllers.Actoins.Action;
import model.Board.Zones;
import model.Card.Card;
import model.Gamer;
import model.Phase;
import view.Printer.Printer;

import java.util.ArrayList;
import java.util.Collections;

public class GameData {

    private int gameStarterId = 0;
    private ArrayList<Gamer> gamers = new ArrayList<>();
    private ArrayList<Action> currentActions = new ArrayList<>();

    private Card selectedCard;
    private int turn;
    private Phase currentPhase = Phase.DRAW;


    public GameData(Gamer firstGamer, Gamer secondGamer) {
        gamers.add(firstGamer);
        gamers.add(secondGamer);
    }

    public boolean isGameOver() {
        return (gamers.get(0).getLifePoint() == 0 || gamers.get(1).getLifePoint() == 0);

    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Gamer getCurrentGamer() {
        return gamers.get(0);
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }

    public Gamer getSecondGamer() {
        return gamers.get(1);
    }

    public int getTurn() {
        return turn;
    }

    public void turnFinished() {
        changeTurn();
        Printer.print(getCurrentGamer().getUsername() + "'s turn");
        turn++;
    }

    public void changeTurn() {
        Collections.swap(gamers, 0, 1);
        gameStarterId = 1 - gameStarterId;
    }

    public void addActionToCurrentActions(Action action) {
        currentActions.add(action);
    }

    public void removeActionFromCurrentActions(Action action) {
        currentActions.remove(action);
    }


    public void moveCardFromOneZoneToAnother(Card card, Zones sourceZone, Zones destinationZone) {
        destinationZone.addCard(sourceZone.removeCard(sourceZone.getId(card)));
    }

    public int getRole(Card card) {

        if (gamers.get(0).getGameBoard().getZone(card) != null) {
            return 0;
        } else if (gamers.get(1).getGameBoard().getZone(card) != null) {
            return 1;
        } else {
            return -1;
        }
    }

    ArrayList<Phase> phases = new ArrayList<>();

    {
        phases.add(Phase.DRAW);
        phases.add(Phase.STANDBY);
        phases.add(Phase.MAIN1);
        phases.add(Phase.BATTLE);
        phases.add(Phase.MAIN2);
        phases.add(Phase.END);
    }

    public void goToNextPhase() {

        int nextPhaseIndex = (phases.indexOf(currentPhase) + 1) % 6;
        currentPhase = phases.get(nextPhaseIndex);
    }

    public void goToEndPhase(){
        currentPhase = Phase.END;
    }

    public void showBoard() {

        Printer.print(getSecondGamer().getBoardForRival());
        Printer.print("--------------------------");
        Printer.print(getCurrentGamer().getBoardForSelf());
    }

    public void finishGame() {
        for (Gamer gamer : gamers) {
            gamer.gameFinished();
        }
    }

    public Gamer getGameStarter() {
        return gamers.get(gameStarterId);
    }

    public Gamer getInvitedGamer() {
        return gamers.get(1 - gameStarterId);
    }
}
