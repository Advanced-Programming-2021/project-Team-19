package model;

import java.util.ArrayList;
import java.util.HashMap;

public enum Phase {
    DRAW("draw phase"),
    STANDBY("stand by phase"),
    MAIN1("main phase 1"),
    MAIN2("main phase 2"),
    BATTLE("battle phase"),
    END("end phase");

    String phaseName;

    Phase(String name){
    }

    public String getPhaseName(){
        return phaseName;
    }
}
