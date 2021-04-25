package Controller;

import Model.User;

import java.util.regex.Matcher;

public class DeckController {
    private User user;

    private static DeckController instance = null;

    private DeckController() {

    }

    public static DeckController getInstance() {
        if (instance == null)
            return new DeckController();
        return instance;
    }

    public void run(User user) {

    }

    private void createDeck(Matcher matcher) {
    }

    private void deleteDeck(Matcher matcher) {
    }

    private void setActiveDeck(Matcher matcher) {
    }

    private void addCardToDeck(Matcher matcher) {
    }

    private void deleteCardFromDeck(Matcher matcher) {
    }

    private void showUserDecks(Matcher matcher) {
    }

    private void showSingleDeck(Matcher matcher) {
    }

    private void showAllCards(Matcher matcher) {
    }

    public void manageDeckMenu(String command) {
    }
}
