package model.Card.Spells;

import model.Card.Monster;
import model.Card.Spell;
import model.Card.SpellAndTraps;
import model.Card.TrapAndSpellTypes.ContinuousEffect;
import model.Card.TrapAndSpellTypes.Undo;

public class ChangeOfHeart extends Spell implements ContinuousEffect, Undo {
    Monster monster;

    @Override
    public void undo() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void checkActivation() {

    }
}
