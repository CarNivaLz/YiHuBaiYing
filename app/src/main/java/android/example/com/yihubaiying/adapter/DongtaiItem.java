package android.example.com.yihubaiying.adapter;

/**
 * Created by Thingvellir on 2017/11/1.
 */

public class DongtaiItem {
    private int userImageId;
    private String nickname;
    private String time;
    private String title;
    private String content;


    private int goodImage;

    public DongtaiItem(int userImageId, String nickname, String time, String title, String content, int goodImage) {
        this.userImageId = userImageId;
        this.nickname = nickname;
        this.time = time;
        this.title = title;
        this.content = content;
        this.goodImage = goodImage;
    }

    public int getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(int userImageId) {
        this.userImageId = userImageId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public int getGoodImage() {
        return goodImage;
    }

    public void setGoodImage(int goodImage) {
        this.goodImage = goodImage;
    }
}
