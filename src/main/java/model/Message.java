package model;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Message {
    private String username;
    private String content;
    private long publishDate;
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Message() {
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public Message(String content, long publishDate) {
        this(null, content, publishDate);
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public Message(String username, String content, long publishDate) {
        this.username = username;
        this.content = content;
        this.publishDate = publishDate;
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public Message(Message m) {
        this.username = m.getUsername();
        this.content = m.getContent();
        this.publishDate = m.getPublishDate();
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public Message clone() {
        return new Message(this);
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public String getFormatDate() {
        return timeFormat.format(this.publishDate) + " (UTC)";
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }

}
