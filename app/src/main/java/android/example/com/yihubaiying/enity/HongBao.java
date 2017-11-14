package android.example.com.yihubaiying.enity;

import android.location.Location;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.id;

/**
 * Created by carnivalnian on 2017/10/29.
 */

public class HongBao  {

    private int id;
    private int number;
    private String title;
    private String snippet;
    private String detail;
    private int[] images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

}
