package controller.DuelControllers;

import controller.DuelControllers.Actoins.Select;
import model.Phase;

public class GameHelp {

    public static void run(Phase phaseName) {
        switch (phaseName) {
            case BATTLE:
                System.out.println("""
                        attack direct
                        attack <id>""");
                Select.help();
                break;
            case MAIN1:
            case MAIN2:
                System.out.println("""
                        summon
                        set
                        set position attack|defence
                        flip summon""");
                Select.help();
                break;
        }
        System.out.println("""
                surrender
                help
                next phase
                show board""");
    }

}
