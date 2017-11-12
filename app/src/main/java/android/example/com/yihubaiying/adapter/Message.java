package android.example.com.yihubaiying.adapter;

/**
 * Created by Thingvellir on 2017/11/5.
 */

public class Message {

    private int userImageId;
    private String title;
    private String content;
    private String time;
    private int stateImageId;

    public Message(int userImageId, String title, String content, String time, int stateImageId) {
        this.userImageId = userImageId;
        this.title = title;
        this.content = content;
        this.time = time;
        this.stateImageId = stateImageId;
    }

    public int getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(int userImageId) {
        this.userImageId = userImageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStateImageId() {
        return stateImageId;
    }

    public void setStateImageId(int stateImageId) {
        this.stateImageId = stateImageId;
    }

}
