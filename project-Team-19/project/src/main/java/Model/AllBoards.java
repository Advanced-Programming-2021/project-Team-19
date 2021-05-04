package Model;

import Model.Board.*;
import View.Printer.Printer;

public class AllBoards {
    DeckZone deckZone = new DeckZone();
    FieldZone fieldZone = new FieldZone();
    GraveYard graveYard = new GraveYard();
    MonsterCardZone monsterCardZone = new MonsterCardZone();
    SpellAndTrapCardZone spellAndTrapCardZone = new SpellAndTrapCardZone();
    Hand hand = new Hand();

    void sendFromOneZoneToOther(Zones firstZone, Zones secondZone, int id) {
        secondZone.addCard(firstZone.removeCard(id));
    }


    public String selfToString(){
        return "\n" +
                fieldZone.toString() + "\t".repeat(6) + graveYard.getSize() + "\n" +
                monsterCardZone.toString() + "\n" +
                spellAndTrapCardZone.toString() + "\n" +
                "\t".repeat(6) + deckZone.getSize() + "\n" +
                hand.selfToString() + "\n";
    }

    public String rivalToString(){
        return hand.rivalToString()+"\n" +
                deckZone.getSize()+"    ".repeat(6)+"\n"+
                spellAndTrapCardZone.toString()+"\n"+
                monsterCardZone.toString()+"\n"+
                graveYard.getSize()+"\t".repeat(6)+fieldZone.toString()+"\n";
    }
}
