package controller.MenuControllers;

import controller.DataBaseControllers.DeckDataBaseController;
import controller.DataBaseControllers.UserDataBaseController;
import controller.Utils;
import model.Card.Card;
import model.Data.DataForClientFromServer;
import model.Deck;
import model.Enums.CardNames;
import model.Enums.MessageType;
import model.User;
import view.Printer.Printer;

import java.nio.file.Files;
import java.util.regex.Matcher;

public class DeckMenuController {

    private String getDeckPath(User user, String deckName) {
        return user.getUsername() + "_" + deckName;
    }

    private static DeckMenuController instance = null;

    private DeckMenuController() {
    }

    public static DeckMenuController getInstance() {
        if (instance == null)
            return new DeckMenuController();
        return instance;
    }

    public DataForClientFromServer run(User user, String command) {


        if (command.matches("deck create (\\w+)"))
            return createDeck(user, Utils.getMatcher(command, "deck create (\\w+)"));
        else if (command.matches("deck delete (\\w+)")) {
            return deleteDeck(user, Utils.getMatcher(command, "deck delete (\\w+)"));
        } else if (command.matches("deck set-active (\\w+)")) {
            return setActiveDeck(user, Utils.getMatcher(command, "deck set-active (\\w+)"));
        } else if (command.matches("deck add-card --card (.+?) --deck (\\w+)( --side|)")) {
            return addCardToDeck(user, Utils.getMatcher(command,
                    "deck add-card --card (.+?) --deck (\\w+)( --side|)"));
        } else if (command.matches("deck rm-card --card (.+?) --deck (\\w+)( --side|)")) {
            return deleteCardFromDeck(user, Utils.getMatcher(command,
                    "deck rm-card --card (.+?) --deck (\\w+)( --side|)"));
        } else if (command.equals("deck show --all")) {
            return showUserDecks(user);
        }
        else if (command.matches("deck show --deck-name (\\w+)( --side|)")){
            return showSingleDeck(user,
                    Utils.getMatcher(command, "deck show --deck-name (\\S+)( --side|)"));
        }
        else if (command.equals("deck show --cards")){
            return showAllCards(user);
        }


        return Utils.getDataSendToClientForInvalidInput();
    }

    private DataForClientFromServer createDeck(User user, Matcher matcher) {

        matcher.find();

        String name = matcher.group(1);

        if (user.getDeckNames().contains(name)) {
            return new DataForClientFromServer("deck with name " + name + " already exists",
                    MessageType.ERROR);

        } else {
            user.addDeck(name);
            DeckDataBaseController.createDeck(user.getUsername(), new Deck(name));
            UserDataBaseController.saveChanges(user);
            return new DataForClientFromServer(
                    "deck created successfully!", MessageType.SUCCESSFUL);
        }

    }

    private DataForClientFromServer deleteDeck(User user, Matcher matcher) {

        matcher.find();

        String name = matcher.group(1);
        if (!user.getDeckNames().contains(name)) {

            return new DataForClientFromServer("deck with name " + name + " does not exist",
                    MessageType.ERROR);
        }
        try {
            if (user.getActiveDeckName().equals(name)) {
                user.setActiveDeckName(null);
            }
        }catch(NullPointerException ignored){}

        Deck deck = DeckDataBaseController.getDeckByName(getDeckPath(user, name));

        for (CardNames cardName : deck.getAllCard()) {
            user.addCard(cardName);
        }

        DeckDataBaseController.removeDeck(user.getUsername(), name);
        user.removeDeck(name);
        UserDataBaseController.saveChanges(user);

        return new DataForClientFromServer("deck deleted successfully",
                MessageType.SUCCESSFUL);

    }

    private DataForClientFromServer setActiveDeck(User user, Matcher matcher) {

        matcher.find();

        String name = matcher.group(1);
        if (!user.getDeckNames().contains(name)) {
            return new DataForClientFromServer(
                    "deck with name " + name + " does not exist", MessageType.ERROR);

        } else {
            user.setActiveDeckName(name);
            UserDataBaseController.saveChanges(user);
            return new DataForClientFromServer(
                    "deck activated successfully", MessageType.SUCCESSFUL);

        }

    }

    private DataForClientFromServer addCardToDeck(User user, Matcher matcher) {

        matcher.find();

        String cardName = matcher.group(1);
        String deckName = matcher.group(2);

        boolean isSideDeck = matcher.group(3).equals(" --side");

        if (!user.getCards().contains(Utils.getCardEnumByName(cardName)))
            return new DataForClientFromServer(
                    "card with name " + cardName + " does not exist", MessageType.ERROR);

        else if (!user.getDeckNames().contains(deckName))
            return new DataForClientFromServer("deck with name " + deckName + " does not exist",
                    MessageType.ERROR);

        else if (isSideDeck && DeckDataBaseController.getDeckByName(getDeckPath(user, deckName)).
                isSideDeckFull())
            return new DataForClientFromServer("side deck is full", MessageType.SUCCESSFUL);

        else if (!isSideDeck && DeckDataBaseController.getDeckByName(getDeckPath(user, deckName)).
                isMainDeckFull())
            return new DataForClientFromServer("main deck is full", MessageType.ERROR);

        else if (DeckDataBaseController.getDeckByName(getDeckPath(user, deckName)).
                isThereThreeCardsInDeck(Utils.getCardEnumByName(cardName)))
            return new DataForClientFromServer(
                    "there are already three cards with name " + cardName + " in deck " + deckName,
                    MessageType.ERROR);

        else if (isSideDeck) {
            Printer.print("card added to deck successfully");
            user.removeCard(Utils.getCardEnumByName(cardName));
            Deck deck = DeckDataBaseController.getDeckByName(getDeckPath(user,deckName));
            deck.addCardToSideDeck(Utils.getCardEnumByName(cardName));
            DeckDataBaseController.changeDeck(user.getUsername(), deck);
            UserDataBaseController.saveChanges(user);
            return new DataForClientFromServer("card added to deck successfully",
                    MessageType.SUCCESSFUL);
        } else {

            user.removeCard(Utils.getCardEnumByName(cardName));
            Deck deck = DeckDataBaseController.getDeckByName(getDeckPath(user,deckName));
            deck.addCardToMainDeck(Utils.getCardEnumByName(cardName));
            DeckDataBaseController.changeDeck(user.getUsername(), deck);
            UserDataBaseController.saveChanges(user);
            return new DataForClientFromServer("card added to deck successfully",
                    MessageType.SUCCESSFUL);
        }

    }

    private DataForClientFromServer deleteCardFromDeck(User user, Matcher matcher) {

        matcher.find();

        String cardName = matcher.group(1);
        String deckName = matcher.group(2);

        boolean isSideDeck = matcher.group(3).equals(" --side");

        if (!user.getDeckNames().contains(deckName))
            return new DataForClientFromServer(
                    "deck with name " + deckName + " does not exist", MessageType.ERROR);

        else if (isSideDeck && !DeckDataBaseController.getDeckByName(getDeckPath(user, deckName)).
                getSideDeckCards().contains(Utils.getCardEnumByName(cardName)))
            return new DataForClientFromServer("card with name " + cardName +
                    " does not exist in side deck", MessageType.ERROR);

        else if (!isSideDeck && !DeckDataBaseController.getDeckByName(getDeckPath(user, deckName)).
                getMainDeckCards().contains(Utils.getCardEnumByName(cardName)))
            return new DataForClientFromServer("card with name " + cardName +
                    " does not exist in main deck", MessageType.ERROR);


        else if (!isSideDeck) {

            user.addCard(Utils.getCardEnumByName(cardName));
            Deck deck = DeckDataBaseController.getDeckByName(getDeckPath(user, deckName));
            deck.removeCardFromMainDeck(Utils.getCardEnumByName(cardName));
            DeckDataBaseController.changeDeck(user.getUsername(), deck);
            UserDataBaseController.saveChanges(user);
            return new DataForClientFromServer("card removed form deck successfully",
                    MessageType.SUCCESSFUL);
        } else {
            Printer.print("card removed form deck successfully");
            user.addCard(Utils.getCardEnumByName(cardName));
            Deck deck = DeckDataBaseController.getDeckByName(getDeckPath(user, deckName));
            deck.removeCardFromSideDeck(Utils.getCardEnumByName(cardName));
            DeckDataBaseController.changeDeck(user.getUsername(), deck);
            UserDataBaseController.saveChanges(user);
            return new DataForClientFromServer("card removed form deck successfully",
                    MessageType.SUCCESSFUL);
        }

    }

    private DataForClientFromServer showUserDecks(User user) {

        StringBuilder returnedData = new StringBuilder();

        returnedData.append("Decks:\n");
        returnedData.append("Active deck:\n");

        String activeDeckName = user.getActiveDeckName();

        if (DeckDataBaseController.getDeckByName(getDeckPath(user, activeDeckName)) != null) {
            returnedData.append(DeckDataBaseController.getDeckByName
                    (getDeckPath(user, activeDeckName)).toString());
        }

        returnedData.append("Other decks:");

        for (String deckName : user.getDeckNames()) {
            if (!deckName.equals(activeDeckName)) {
                returnedData.append(DeckDataBaseController.getDeckByName(
                        getDeckPath(user, deckName)).toString());
            }
        }

        return new DataForClientFromServer(returnedData.toString(), MessageType.DECK);

    }

    private DataForClientFromServer showSingleDeck(User user, Matcher matcher) {

        matcher.find();

        String name = matcher.group(1);

        boolean isSideDeck = matcher.group(2).equals(" --side");

        if (!user.getDeckNames().contains(name)) {

            return new DataForClientFromServer("deck with name " + name + " does not exist",
                    MessageType.ERROR);
        }
        else{
            Deck deck=DeckDataBaseController.getDeckByName(getDeckPath(user,name));
            if(isSideDeck){
                return new DataForClientFromServer(deck.detailedToStringSide(),MessageType.DECK);
            }
            else{
                return new DataForClientFromServer(deck.detailedToStringMain(),MessageType.DECK);
            }
        }

    }

    private DataForClientFromServer showAllCards(User user) {
        StringBuilder temp=new StringBuilder();
        for(Card card:user.getCardsSorted()){
            temp.append(card.toString()).append("\n");
        }
        return new DataForClientFromServer(temp.toString(),MessageType.DECK);
    }



}