package model;

public class Message {
    private String username;
    private String content;
    private long publishDate;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }

    public Message(String content, long publishDate) {
        this(null, content, publishDate);
    }

    public Message(String username, String content, long publishDate) {
        this.username = username;
        this.content = content;
        this.publishDate = publishDate;
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
    public Message clone(){
        return new Message(this);
    }
    public Message(Message m){
        this.username=m.getUsername();
        this.content=m.getContent();
        this.publishDate=m.getPublishDate();
    }

}
