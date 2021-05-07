package Model.Board;

import Model.Card.Card;
import Model.Card.Spell;
import Model.Enums.SpellCardMods;

import java.util.ArrayList;

public class SpellAndTrapCardZone extends Zones {

    ArrayList<Spell> allCards=new ArrayList<>();


    {
        for(int i=0;i<5;i++){
            allCards.add(null);
        }
    }

    public Card getCard(int id) {

        return null;
    }

    public Card removeCard(int id) {

        return null;
    }

    public void addCard(Card card) {


    }

    @Override
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder("\t");
        for(int i=0;i<5;i++){
            if(allCards.get(i).getSpellCardMod().equals(SpellCardMods.EMPTY)){
                stringBuilder.append("E\t");
            }
            else if(allCards.get(i).getSpellCardMod().equals(SpellCardMods.HIDDEN)){
                stringBuilder.append("H\t");
            }
            else{
                stringBuilder.append("O\t");
            }

        }
        return stringBuilder.toString();
    }
}
