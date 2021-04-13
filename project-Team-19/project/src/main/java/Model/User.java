package Model;

import Controller.*;

public class User {

    private String username;
    String password;
    String nickname;

    public User(String username, String nickName, String password){

        setUsername(username);
        setPassword(password);
        setNickname(nickName);

        DataBaseController.createUser(this);
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

}
