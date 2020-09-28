package Model;

import java.util.ArrayList;
import java.util.Date;

public class ChatManager {

    private ArrayList<Message> messageList = new ArrayList<Message>();

    private static final ChatManager chatManager = new ChatManager();

    private ChatManager() {
    }

    public static ChatManager getInstance(){
        return chatManager;
    }

    /**
     * @param user    represents the name of the user and message
     * @param message is a plain text (that may include any character)
     *
     *                The message is dated automatically (in UTC) and is returned to the called.
     */
    public void postMessage(String user, String message) {
        messageList.add(new Message(user, message, new Date()));
    }

    public ArrayList<Message> listMessages() {

    }

    public ArrayList<Message> listMessages(long startDate, long endDate) {

    }

    public void clearChat() {

    }

    public void clearChat(long startDate, long endDate) {

    }
}
