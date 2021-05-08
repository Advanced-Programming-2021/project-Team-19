package model.Card;

import model.Enums.SpellCardMods;
import model.Enums.SpellsAndTraps.SpellTypes;

public abstract class Spell extends Card {
    private SpellTypes spellType;
    private int activationTurn;
    private SpellCardMods spellCardMod;
    public SpellTypes getSpellType() {
        return spellType;
    }

    public void setSpellType(SpellTypes spellType) {
        this.spellType = spellType;
    }

    public int getActivationTurn() {
        return activationTurn;
    }

    public void setActivationTurn(int activationTurn) {
        this.activationTurn = activationTurn;
    }

    public SpellCardMods getSpellCardMod() {
        return spellCardMod;
    }

    public void setSpellCardMod(SpellCardMods spellCardMod) {
        this.spellCardMod = spellCardMod;
    }



    public abstract void activate();
}
