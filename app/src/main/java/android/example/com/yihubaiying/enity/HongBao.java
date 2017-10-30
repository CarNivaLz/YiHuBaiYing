package android.example.com.yihubaiying.enity;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

/**
 * Created by carnivalnian on 2017/10/29.
 */

public class HongBao  {
    private String title;
    private String snipped;
    private LatLng latLng;
    private int number;
    private int id;

    public void setNumber(int number){
        this.number=number;
    }

    public int getNumber(){
        return number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSnipped(String snipped) {
        this.snipped = snipped;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getSnipped() {
        return snipped;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public int getId() {
        return id;
    }
}
