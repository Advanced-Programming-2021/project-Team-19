package Model.Card;

import Model.Enums.SpellsAndTraps.SpellTypes;

public abstract class Spell extends Card {
    SpellTypes spellType;

    public abstract void activate();
}
