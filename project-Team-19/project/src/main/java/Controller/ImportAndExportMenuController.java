package Controller;

import Controller.DataBaseControllers.CardDataBaseController;
import Controller.DataBaseControllers.DataBaseController;
import Model.Enums.CardNames;
import View.GetInput;
import View.Printer.Printer;

import java.util.regex.Matcher;

public class ImportAndExportMenuController extends MenuController {

    private static ImportAndExportMenuController instance = null;

    private ImportAndExportMenuController() {
        super("Import/Export Menu");
    }

    public static ImportAndExportMenuController getInstance() {
        if (instance == null) {
            instance = new ImportAndExportMenuController();
        }
        return instance;
    }

    private void importCard(Matcher matcher) {

    }

    private void exportCard(Matcher matcher) {

        matcher.find();

        CardNames cardName = Utils.getCardEnumByName(matcher.group(1));

        if(cardName == null){
            System.out.println("invalid card name");
            return;
        }
        System.out.println(DataBaseController.makeObjectJson
                (CardDataBaseController.getCardObjectByCardName(cardName)));

    }


    public void run() {

        String command;
        command = GetInput.getString();

        while (true){

            if(command.matches("import card .+")){
                importCard(Utils.getMatcher(command, "import card (.+)"));
            }
            else if (command.matches("export card .+")){
                exportCard(Utils.getMatcher(command, "export card (.+)"));
            }
            else if (command.matches("menu exit")) {
                break;
            }
            else if (command.startsWith("menu ")) {
                handleMenuOrders(command);
            }
            else if(command.matches("help")){
                help();
            }
            else {
                Printer.printInvalidCommand();
            }
        }

    }

    private void help(){
        System.out.print("""
                import card [card name]
                export card [card name]
                 help
                menu show-current
                menu [menu name]
                menu exit               
                """);
    }
}
