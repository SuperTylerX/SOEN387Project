package Model;

import java.util.Date;

public class Message {

    String username;
    String content;
    long publishDate;

    public Message(String username, String content, Date publishDate) {
        this.username = username;
        this.content = content;
        this.publishDate = publishDate.getTime();
    }
}
