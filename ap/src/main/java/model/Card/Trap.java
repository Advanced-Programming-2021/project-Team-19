package model.Card;

import com.google.gson.annotations.Expose;
import model.Enums.LabelsForActivatingTraps;
import model.Enums.SpellCardMods;
import model.Enums.SpellsAndTraps.TrapTypes;

public abstract class Trap extends Card {

    @Expose
    TrapTypes trapType;

    @Expose
    LabelsForActivatingTraps activationLabel;

    SpellCardMods spellCardMod;

    public TrapTypes getTrapType() {
        return trapType;
    }

    public void setTrapType(TrapTypes trapType) {
        this.trapType = trapType;
    }

    public LabelsForActivatingTraps getActivationLabel() {
        return activationLabel;
    }

    public void setActivationLabel(LabelsForActivatingTraps activationLabel) {
        this.activationLabel = activationLabel;
    }

    public SpellCardMods getSpellCardMod() {
        return spellCardMod;
    }

    public void setSpellCardMod(SpellCardMods spellCardMod) {
        this.spellCardMod = spellCardMod;
    }

    public boolean labelExists() {
        return false;
    }


    public void counter() {

    }

    public abstract void activate();

}
