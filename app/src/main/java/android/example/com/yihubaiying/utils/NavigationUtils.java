package android.example.com.yihubaiying.utils;

/**
 * Created by carnivalnian on 2017/10/29.
 */

import android.content.Context;
import android.example.com.yihubaiying.activity.BaseApplication;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;

/**
 * Created by Teprinciple on 2016/8/22.
 * 跳转到高德地图进行导航
 */
public class NavigationUtils {

    private static Context mContext;

    public static void Navigation(LatLng latLng) {
        mContext = BaseApplication.getIntance().getBaseContext();


    }
}