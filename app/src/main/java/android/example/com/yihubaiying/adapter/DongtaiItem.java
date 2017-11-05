package android.example.com.yihubaiying.adapter;

/**
 * Created by Thingvellir on 2017/11/1.
 */

public class DongtaiItem {
    private String miaoshu;
    private String nicheng;
    private int adv_image;
    private String time;
    private int imageId;

    public DongtaiItem(String miaoshu, String nicheng, int adv_image, String time, int imageId) {
        this.miaoshu = miaoshu;
        this.nicheng = nicheng;
        this.adv_image = adv_image;
        this.time = time;
        this.imageId = imageId;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
    }

    public int getAdv_image() {
        return adv_image;
    }

    public void setAdv_image(int adv_image) {
        this.adv_image = adv_image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
