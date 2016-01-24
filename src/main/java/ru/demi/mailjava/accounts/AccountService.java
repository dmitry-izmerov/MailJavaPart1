package ru.demi.mailjava.accounts;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author demi
 * Created on 22.01.16
 */
public class AccountService {
    private final Map<String, UserProfile> signedUpUsers = new ConcurrentHashMap<>();

    public void singUp(String login, String password) {
        signedUpUsers.put(login, new UserProfile(login, password));
    }

    public boolean isSingIn(String login, String password) {
        UserProfile profile = signedUpUsers.get(login);
        return profile != null && profile.getPassword().equals(password);
    }
}
