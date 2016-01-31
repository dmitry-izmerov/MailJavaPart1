package ru.demi.mailjava.chat;

/**
 * @author demi
 * @date 31.01.16
 */
public class ChatService {

    public void sendMessage(ChatWebSocket user, String data) {
        try {
            user.sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
