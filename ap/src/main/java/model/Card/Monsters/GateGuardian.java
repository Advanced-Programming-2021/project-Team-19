package model.Card.Monsters;

import model.Card.EffectTypes.Flip;
import model.Card.Monster;

public class GateGuardian extends Monster implements Flip {
    @Override
    public boolean handleFlip() {
        return false;
    }
}
