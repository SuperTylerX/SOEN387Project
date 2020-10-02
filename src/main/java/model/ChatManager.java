package model;

import java.util.ArrayList;
import java.util.Date;

public class ChatManager {

    private ArrayList<Message> messageList = new ArrayList<Message>();

    private static final ChatManager chatManager = new ChatManager();

    private ChatManager() {
    }

    public static ChatManager getInstance() {
        return chatManager;
    }

    /**
     * @param user    represents the name of the user and message
     * @param message is a plain text (that may include any character)
     *                <p>
     *                The message is dated automatically (in UTC) and is returned to the called.
     */
    public void postMessage(String user, String message) {
        if (user == null || user.isEmpty()) {
            messageList.add(new Message(message, new Date().getTime()));
        } else {
            messageList.add(new Message(user, message, new Date().getTime()));
        }
    }

    public ArrayList<Message> listMessages() {

        return null;
    }

    public ArrayList<Message> listMessages(long startDate, long endDate) {

        return null;
    }

    public void clearChat() {
        this.messageList = new ArrayList<Message>();
    }

    public void clearChat(long startDate, long endDate) {
        for (int i = this.messageList.size() - 1; i >= 0; i--) {
            if (this.messageList.get(i).getPublishDate() >= startDate && this.messageList.get(i).getPublishDate() <= endDate) {
                this.messageList.remove(i);
            }
        }
    }
}
