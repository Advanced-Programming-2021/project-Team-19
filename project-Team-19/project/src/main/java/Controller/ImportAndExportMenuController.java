package Controller;

import java.util.regex.Matcher;

class ImportAndExportMenuController {

    private static ImportAndExportMenuController instance=null;

    private ImportAndExportMenuController(){}

    public static ImportAndExportMenuController getInstance(){
        if(instance==null){
            instance=new ImportAndExportMenuController();
        }
        return instance;
    }

    private void importCard(Matcher matcher){

    }

    private void exportCard(Matcher matcher){

    }

    public void run(){}
}
