package model.Card.EffectMonsters;

import model.Card.EffectTypes.Flip;
import model.Card.Monster;

public class GateGuardian extends Monster implements Flip {
    @Override
    public boolean handleFlip() {
        return false;
    }
}