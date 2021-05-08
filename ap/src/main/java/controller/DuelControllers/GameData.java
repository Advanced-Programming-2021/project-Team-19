package controller.DuelControllers;

import model.Board.Zones;
import model.Card.Card;
import model.Gamer;

import java.util.ArrayList;

public class GameData {
    ArrayList<Gamer> gamers = new ArrayList<>();
    private Card selectedCard;
    private int turn;

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

}
