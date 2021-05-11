package model.Card;

import com.google.gson.annotations.Expose;
import controller.DuelControllers.GameData;
import model.Enums.SpellCardMods;
import model.Enums.SpellsAndTraps.SpellTypes;

public abstract class SpellAndTraps extends Card {

    @Expose
    private SpellCardMods spellCardMod;

    public abstract void activate();

    public SpellCardMods getSpellCardMod() {
        return spellCardMod;
    }

    public void setSpellCardMod(SpellCardMods spellCardMod) {
        this.spellCardMod = spellCardMod;
    }

    public abstract boolean canActivate(GameData gameData);
}
