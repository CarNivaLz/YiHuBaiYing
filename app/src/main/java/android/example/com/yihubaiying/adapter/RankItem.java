package android.example.com.yihubaiying.adapter;

/**
 * Created by Thingvellir on 2017/10/31.
 */

public class RankItem {
    private String ranknum;
    private int userImageId;
    private String nickName;
    private int diamondImageId;
    private String description;
    private int arrowImageId;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public RankItem(String ranknum, int userImageId, String nickName, int diamondImageId, String description, int arrowImageId) {
        this.ranknum = ranknum;
        this.userImageId = userImageId;
        this.nickName = nickName;
        this.diamondImageId = diamondImageId;
        this.description = description;
        this.arrowImageId = arrowImageId;
    }

    public String getRanknum() {
        return ranknum;
    }

    public void setRanknum(String ranknum) {
        this.ranknum = ranknum;
    }

    public int getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(int userImageId) {
        this.userImageId = userImageId;
    }

    public int getDiamondImageId() {
        return diamondImageId;
    }

    public void setDiamondImageId(int diamondImageId) {
        this.diamondImageId = diamondImageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getArrowImageId() {
        return arrowImageId;
    }

    public void setArrowImageId(int arrowImageId) {
        this.arrowImageId = arrowImageId;
    }
}
