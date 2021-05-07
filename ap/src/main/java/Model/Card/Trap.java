package Model.Card;

import Model.Enums.LabelsForActivatingTraps;
import Model.Enums.SpellCardMods;
import Model.Enums.SpellsAndTraps.TrapTypes;

public abstract class Trap extends Card {
    TrapTypes trapType;
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
