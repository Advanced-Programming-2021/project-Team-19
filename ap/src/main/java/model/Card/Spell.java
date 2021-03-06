package model.Card;

import com.google.gson.annotations.Expose;
import controller.DuelControllers.GameData;
import model.Enums.CardFamily;
import model.Enums.SpellCardMods;
import model.Enums.SpellsAndTraps.SpellTypes;

public abstract class Spell extends SpellAndTraps{

    private SpellTypes spellType;

    @Expose
    private int activationTurn;
    {
        setCardFamily(CardFamily.SPELL);
    }
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

    public boolean canActivate(GameData gameData){return false;}

}
