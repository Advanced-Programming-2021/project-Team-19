package controller.DuelControllers;

import model.Board.Zones;
import model.Card.Card;
import model.Gamer;
import model.Phase;

import java.util.ArrayList;
import java.util.HashMap;

public class GameData {

    private ArrayList<Gamer> gamers = new ArrayList<>();
    private Card selectedCard;
    private int turn;
    private Phase currentPhase = Phase.DRAW;

    public GameData(Gamer firstGamer, Gamer secondGamer) {
        gamers.add(firstGamer);
        gamers.add(secondGamer);
    }

    public boolean isGameOver() {
        return false;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Gamer getFirstGamer(){
        return gamers.get(0);
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }

    public Gamer getSecondGamer(){
        return gamers.get(1);
    }

    public int getTurn() {
        return turn;
    }

    public void turnFinished(){
        turn++;
    }


    public void moveCardFromOneZoneToAnother(Card card, Zones sourceZone, Zones destinationZone){
        destinationZone.addCard(sourceZone.removeCard(sourceZone.getId(card)));
    }

    public int getRole(Card card){

        if(gamers.get(0).getGameBoard().getZone(card)!=null){
            return 0;
        }
        else if(gamers.get(1).getGameBoard().getZone(card)!=null){
            return 1;
        }
        else{
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

    public void goToNextPhase(){

        int nextPhaseIndex = (phases.indexOf(currentPhase) + 1) % 6;
        currentPhase = phases.get(nextPhaseIndex);
    }

}
