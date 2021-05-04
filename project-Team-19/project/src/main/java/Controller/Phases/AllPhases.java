package Controller.Phases;


import Controller.Utils;

import java.util.regex.Matcher;

public class AllPhases {
    boolean commandIsDone;
    public void run(String command){
        commandIsDone=false;
        selectMonster(Utils.getMatcher(command,"select --monster (\\d)"));
        selectOpponentMonster(Utils.getMatcher(command,"select (?=.*?--monster)(?=.*?--opponent)--\\S+ --\\S+ (\\d)"));
        selectSpell(Utils.getMatcher(command,"select --spell (\\d)"));
        selectOpponentSpell(Utils.getMatcher(command,"select (?=.*?--spell)(?=.*?--opponent)--\\S+ --\\S+ (\\d)"));
        selectField(Utils.getMatcher(command,"select --field"));
        selectOpponentField(Utils.getMatcher(command,"select (?=.*?--field)(?=.*?--opponent)--\\S+ --\\S+"));
        selectHand(Utils.getMatcher(command,"select --hand (\\d)"));

    }

    private void selectMonster(Matcher matcher){
        if(matcher.matches()){
            commandIsDone=true;
            int selectIndex=Integer.parseInt(matcher.group(1));

        }
    }

    private void selectOpponentMonster(Matcher matcher){
        if(matcher.matches()){
            commandIsDone=true;
            int selectIndex=Integer.parseInt(matcher.group(1));

        }
    }

    private void selectSpell(Matcher matcher){
        if(matcher.matches()){
            commandIsDone=true;
            int selectIndex=Integer.parseInt(matcher.group(1));

        }
    }

    private void selectOpponentSpell(Matcher matcher){
        if(matcher.matches()){
            commandIsDone=true;
            int selectIndex=Integer.parseInt(matcher.group(1));
        }
    }

    private void selectField(Matcher matcher){
        if(matcher.matches()){
            commandIsDone=true;

        }
    }

    private void selectOpponentField(Matcher matcher){
        if(matcher.matches()){
            commandIsDone=true;

        }
    }

    private void selectHand(Matcher matcher){
        if(matcher.matches()){
            commandIsDone=true;
            int selectIndex=Integer.parseInt(matcher.group(1));
        }
    }
}
