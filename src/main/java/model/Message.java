package model;

public class Message {
    private String username;
    private String content;
    private long publishDate;

    public Message(){
    }

    public Message(String content, long publishDate){
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

}
