package Model;

import Controller.*;
import Model.Enums.CardNames;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private String nickname;
    private ArrayList<Integer> deckIDs = new ArrayList<>();
    private int activeDeckID;
    private int score;
    private int credit;
    private ArrayList<CardNames> cards = new ArrayList<>();

    public User(String username, String nickName, String password) {

        setUsername(username);
        setPassword(password);
        setNickname(nickName);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public ArrayList<Integer> getDeckIDs() {
        return deckIDs;
    }

    public void setDeckIDs(ArrayList<Integer> deckIDs) {
        this.deckIDs = deckIDs;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }

    public static User getUserByGson(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
            return gson.fromJson(reader, User.class);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public boolean isPasswordCorrect(String password) {
        return password.matches(this.password);
    }
}
