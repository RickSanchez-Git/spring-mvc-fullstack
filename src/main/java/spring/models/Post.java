package spring.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Date;

public class Post {
    private int id;
    private Date time;

    @NotEmpty(message = "You can't post empty messages")
    @Size(min = 1, max = 100, message = "You can't post more then 100 symbols")
    private String postText;

    public Post() {
        this.time = new Date();
    }

    public Post(int id, String postText) {
        this.id = id;
        this.postText = postText;
        this.time = new Date();
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public Date getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
