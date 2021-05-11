package model.Card.Spells;

import model.Card.Spell;
import model.Card.SpellAndTraps;
import model.Card.TrapAndSpellTypes.Undo;
import model.Enums.MonsterEnums.MonsterType;

import java.util.ArrayList;

public class IncreaserType1 extends Spell implements Undo {
    public ArrayList<MonsterType> monsterTypes = new ArrayList<>();
    public int amountToIncreaseAttack;
    public int amountToIncreaseDefence;

    @Override
    public void activate() {

    }

    @Override
    public void undo() {

    }
}
