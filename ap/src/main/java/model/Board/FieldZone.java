package model.Board;

import model.Card.Card;

public class FieldZone extends Zones {

    private Card card;

    public Card getCard(int id) {
        return card;
    }

    public Card removeCard(int id) {
        Card temp=card;
        card=null;
        return temp;
    }

    public int getId(Card card){
        if(card.equals(this.card)){
            return 0;
        }
        else{
            return -1;
        }
    }

    public void addCard(Card card) {
        this.card=card;
    }

    public String toString(){
        if(card==null){
            return "E";
        }
        else{
            return "O";
        }
    }
}
