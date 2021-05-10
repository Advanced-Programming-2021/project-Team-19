package view.Menu;


import controller.Utils;
import view.GetInput;
import view.Printer.Printer;

import java.util.regex.Matcher;

public class ShopMenu extends Menu {

    public ShopMenu() {
        super("Shop Menu");
    }

    private static ShopMenu instance = null;

    public static ShopMenu getInstance() {

        if (instance == null) {
            instance = new ShopMenu();
        }
        return instance;
    }


    public void run(String username) {

        setUsername(username);

        String command;

        while (true) {

            command = GetInput.getString();

            if (command.matches("shop buy (.+)")) {
                buyGoods(Utils.getMatcher(command, "shop buy (.+)"));
            } else if (command.equals("shop show --all")) {
                showAllCards(Utils.getMatcher(command, "shop show --all"));
            } else if (command.equals("menu exit")) {
                break;
            } else if (command.startsWith("menu ")) {
                handleMenuOrders(command);
            } else if (command.equals("help")) {
                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
    }

    private void buyGoods(Matcher matcher) {

        sendCommandToServer1(matcher);
    }

    private void showAllCards(Matcher matcher) {
        sendCommandToServer1(matcher);
    }

    private void help() {
        System.out.println("""
                shop buy <card name>
                shop show --all
                help
                menu show-current
                menu [menu name]
                menu exit""");
    }
}
