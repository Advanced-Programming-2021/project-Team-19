package Controller;

import Controller.DataBaseControllers.DeckDataBaseController;
import Controller.DataBaseControllers.UserDataBaseController;
import Model.Deck;
import Model.Enums.CardNames;
import Model.User;
import View.GetInput;
import View.Printer.Printer;

import java.util.regex.Matcher;

public class DeckController extends MenuController{

    boolean commandIsDone=false;

    private User user;

    private String getPath(String deckName){
        return user.getUsername()+"_"+deckName;
    }

    private static DeckController instance = null;

    private DeckController() {
        super("Deck Menu");

    }

    public static DeckController getInstance() {
        if (instance == null)
            return new DeckController();
        return instance;
    }

    public void run(User user) {
        this.user=user;
        String command= GetInput.getString();
        while(!command.equals("menu exit")) {
            commandIsDone=false;
            createDeck(Utils.getMatcher(command,"deck create (\\S+)"));
            deleteDeck(Utils.getMatcher(command,"deck delete (\\S+)"));
            setActiveDeck(Utils.getMatcher(command,"deck set-active (\\S+)"));
            addCardToDeck(Utils.getMatcher(command,"deck add-card --card (\\S+) --deck (\\S+) (--side|)"));
            deleteCardFromDeck(Utils.getMatcher(command,"deck rm-card --card (\\S+) --deck (\\S+) --side"));
            showUserDecks(Utils.getMatcher(command,"deck show --all"));
            showSingleDeck(Utils.getMatcher(command,"deck show --deck-name (\\S+) --side"));
            showAllCards(Utils.getMatcher(command,"deck show --cards"));
            changeMenu(command);
            if(!commandIsDone){
                System.out.println("Hello");
            }
            command=GetInput.getString();
        }

    }

    private void createDeck(Matcher matcher) {
        if(matcher.matches()){
            commandIsDone=true;
            String name=matcher.group(1);
            if(user.getDeckNames().contains(name)){
                Printer.print("deck with name "+name+" already exists");
            }
            else{
                user.addDeck(name);
                DeckDataBaseController.createDeck(user.getUsername(),new Deck(name));
                UserDataBaseController.saveChanges(user);
                Printer.print("deck created successfully!");
            }
        }
    }

    private void deleteDeck(Matcher matcher) {
        if(matcher.matches()){
            commandIsDone=true;
            String name=matcher.group(1);
            if(!user.getDeckNames().contains(name)){
                Printer.print("deck with name "+name+" does not exist");
            }
            else{
                if(user.getActiveDeckName().equals(name)){
                    user.setActiveDeckName(null);
                }
                Deck deck= DeckDataBaseController.getDeckByName(getPath(name));
                for(CardNames cardName:deck.getAllCard()){
                    user.addCard(cardName);
                }
                DeckDataBaseController.removeDeck(user.getUsername(),name);
                user.removeDeck(name);
                UserDataBaseController.saveChanges(user);
                Printer.print("deck deleted successfully");
            }
        }
    }

    private void setActiveDeck(Matcher matcher) {
        if(matcher.matches()){
            commandIsDone=true;
            String name=matcher.group(1);
            if(!user.getDeckNames().contains(name)){
                Printer.print("deck with name "+name+" does not exist");
            }
            else{
                user.setActiveDeckName(name);
                UserDataBaseController.saveChanges(user);
                Printer.print("deck activated successfully");
            }
        }
    }

    private void addCardToDeck(Matcher matcher) {
        if(matcher.matches()){
            commandIsDone=true;
            String cardName=matcher.group(1);
            String deckName=matcher.group(2);
            boolean isSideDeck=matcher.group(3).equals("--side");
            if(!user.getCards().contains(CardNames.valueOf(cardName))){
                Printer.print("card with name "+cardName+" does not exist");
            }
            else if(!user.getDeckNames().contains(deckName)){
                Printer.print("deck with name "+deckName+" does not exist");
            }
            else if(isSideDeck&&DeckDataBaseController.getDeckByName(getPath(deckName)).isSideDeckFull()){
                Printer.print("side deck is full");
            }
            else if(!isSideDeck&&DeckDataBaseController.getDeckByName(getPath(deckName)).isMainDeckFull()){
                Printer.print("main deck is full");
            }
            else if(DeckDataBaseController.getDeckByName(getPath(deckName)).isThereThreeCardsInDeck(cardName)){
                Printer.print("there are already three cards with name "+cardName+" in deck "+deckName);
            }
            else if(isSideDeck){
                Printer.print("card added to deck successfully");
                user.removeCard(CardNames.valueOf(cardName));
                Deck deck=DeckDataBaseController.getDeckByName(deckName);
                deck.addCardToSideDeck(CardNames.valueOf(cardName));
                DeckDataBaseController.changeDeck(user.getUsername(),deck);
            }
            else{
                Printer.print("card added to deck successfully");
                user.removeCard(CardNames.valueOf(cardName));
                Deck deck=DeckDataBaseController.getDeckByName(deckName);
                deck.addCardToMainDeck(CardNames.valueOf(cardName));
                DeckDataBaseController.changeDeck(user.getUsername(),deck);
            }

        }
    }

    private void deleteCardFromDeck(Matcher matcher) {
        if(matcher.matches()){
            commandIsDone=true;
            String cardName=matcher.group(1);
            String deckName=matcher.group(2);
            boolean isSideDeck=matcher.group(3).equals("--side");
            if(!user.getDeckNames().contains(deckName)){
                Printer.print("deck with name "+deckName+" does not exist");
            }
            else if(isSideDeck&&!DeckDataBaseController.getDeckByName(getPath(deckName)).getSideDeckCards().contains(CardNames.valueOf(cardName))){
                Printer.print("card with name "+cardName+" does not exist in side deck");
            }
            else if(!isSideDeck&&!DeckDataBaseController.getDeckByName(getPath(deckName)).getMainDeckCards().contains(CardNames.valueOf(cardName))){
                Printer.print("card with name "+cardName+" does not exist in main deck");
            }
            else if(!isSideDeck){
                Printer.print("card removed form deck successfully");
                user.addCard(CardNames.valueOf(cardName));
                Deck deck=DeckDataBaseController.getDeckByName(getPath(deckName));
                deck.removeCardFromMainDeck(CardNames.valueOf(cardName));
                DeckDataBaseController.changeDeck(user.getUsername(),deck);
            }
            else{
                Printer.print("card removed form deck successfully");
                user.addCard(CardNames.valueOf(cardName));
                Deck deck=DeckDataBaseController.getDeckByName(getPath(deckName));
                deck.removeCardFromSideDeck(CardNames.valueOf(cardName));
                DeckDataBaseController.changeDeck(user.getUsername(),deck);
            }

        }
    }

    private void showUserDecks(Matcher matcher) {
        if(matcher.matches()){
            commandIsDone=true;
            String activeDeckName=user.getActiveDeckName();
            Printer.print("Decks:");
            Printer.print("Active deck:");
            if(DeckDataBaseController.getDeckByName(getPath(activeDeckName))!=null) {
                Printer.print(DeckDataBaseController.getDeckByName(getPath(activeDeckName)).toString());
            }
            Printer.print("Other decks:");
            for(String deckName:user.getDeckNames()){
                if(!deckName.equals(activeDeckName)){
                    Printer.print(DeckDataBaseController.getDeckByName(getPath(deckName)).toString());
                }
            }

        }
    }

    private void showSingleDeck(Matcher matcher) {
        if(matcher.matches()){
            commandIsDone=true;


        }
    }

    private void showAllCards(Matcher matcher) {
        if(matcher.matches()){
            commandIsDone=true;
        }
    }

    private void changeMenu(String command){
        if(command.startsWith("menu ")){
            commandIsDone=true;
            handleMenuOrders(command);
        }
    }

}


