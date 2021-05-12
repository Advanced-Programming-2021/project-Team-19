package model.Card;

import com.google.gson.annotations.Expose;
import controller.DuelControllers.GameData;
import model.Enums.SpellCardMods;
import model.Enums.SpellsAndTraps.SpellTypes;

public abstract class SpellAndTraps extends Card {

    @Expose
    int setTurn = 0;
    @Expose
    private SpellCardMods spellCardMod;
    @Expose
    private boolean wasActivated = false;

    public abstract void activate();

    public SpellCardMods getSpellCardMod() {
        return spellCardMod;
    }

    protected void setSpellCardMod(SpellCardMods spellCardMod) {
        this.spellCardMod = spellCardMod;
    }

    public abstract boolean canActivate(GameData gameData);

    public boolean handleSet(GameData gameData){
        setSpellCardMod(SpellCardMods.HIDDEN);
        setTurn = gameData.getTurn();
        return true;
    }

    public boolean canActivateThisTurn(GameData gameData){

        if(setTurn == gameData.getTurn()){
            return false;
        }
        return true;
    }
}
