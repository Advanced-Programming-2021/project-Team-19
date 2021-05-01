package Model.Board;

import Model.Card.Card;
import Model.Enums.CardMod;

import java.util.ArrayList;

public class MonsterCardZone extends Zones {
    private ArrayList<Card> cardsInMonsterZone = new ArrayList<>();
    private ArrayList<CardMod> cardsMods = new ArrayList<>();

    {
        for (int i = 0; i < 5; i++) {
            cardsInMonsterZone.add(null);
            cardsMods.add(CardMod.EMPTY);
        }
    }


    public Card getCardById(int id, boolean selfRequest) {
        if (selfRequest) {
            return cardsInMonsterZone.get(hashNumber(id));
        } else {
            return cardsInMonsterZone.get(hashRivalNumber(id));
        }
    }

    public Card removeCard(int id) {

        return null;
    }

    public void addCard(Card card) {


    }

    public void changeModById(int id, CardMod cardMod) {


    }


}
