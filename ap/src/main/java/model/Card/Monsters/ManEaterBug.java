package model.Card.Monsters;

import model.Card.EffectTypes.Flip;
import model.Card.Monster;

public class ManEaterBug extends Monster implements Flip {
    @Override
    public boolean handleFlip() {
        return false;
    }
}
