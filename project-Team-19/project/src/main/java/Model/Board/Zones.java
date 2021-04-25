package Model.Board;

import Model.Card.Card;

public interface Zones {

    public Card removeCard(int id);

    public void addCard(Card card);
}
