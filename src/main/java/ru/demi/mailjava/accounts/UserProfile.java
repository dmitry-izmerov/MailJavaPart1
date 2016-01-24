package ru.demi.mailjava.accounts;

/**
 * Created by demi
 * on 22.01.16.
 */
public class UserProfile {
    private final String login;
    private final String password;

    public UserProfile(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
