package android.example.com.yihubaiying.fragment.fragment_hongbaomap;

import android.content.Context;
import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.loader.GlideImageLoader;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapFragment;
import com.amap.api.maps.Projection;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.amap.api.col.sl3.au.b;


/**
 * Created by carnivalnian on 2017/10/21.
 */

public  class Fragment_HongBao extends Fragment implements AMap.OnMyLocationChangeListener,
        AMap.OnMarkerClickListener,
        AMap.OnCameraChangeListener{

    public static boolean isInit = false;

    private Banner banner;

    private AMap aMap;
    private TextureMapView mapView;
    private UiSettings uiSettings;
    private MyLocationStyle myLocationStyle;


    private Marker markerLocal;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_hongbao,container,false);
        mapView=(TextureMapView)view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        initView(view);
        return view;
    }


    private void initView(View view) {
        initBanner(view);

        if (aMap == null) {
            aMap = mapView.getMap();
    }else{
            aMap.clear();
            aMap=mapView.getMap();
         }
        setMapCustomStyleFile(getContext());
        setUpMap();
        aMap.setOnMyLocationChangeListener(this);

    }

    public void initBanner(View view){
        banner=(Banner)view.findViewById(R.id.banner);
        List<Integer> list=new ArrayList<>();
        List<String> mlist=new ArrayList<>();
        list.add(R.drawable.banner_image1);
        list.add(R.drawable.banner_image2);
        list.add(R.drawable.banner_image3);
        mlist.add("中海国际新房优惠");
        mlist.add("中海国际新盘开售");
        mlist.add("中海国际房价一览");
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImages(list)
                .setImageLoader(new GlideImageLoader())
                .setBannerTitles(mlist)
                .start();
    }

    public void setUpMap(){
        aMap.setMapCustomEnable(true);
        //amp
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.gps_point));
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //定位间隔
        //myLocationStyle.interval(2000);
        aMap.setMyLocationStyle(myLocationStyle);

        //ui控件
        uiSettings=aMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setScaleControlsEnabled(false);
        uiSettings.setZoomControlsEnabled(false);
        //启动
        aMap.setMyLocationEnabled(true);

        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
    }
    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();


    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onMyLocationChange(Location location) {
        // 定位回调监听
        if(location != null) {
            Log.e("amap", "onMyLocationChange 定位成功， lat: " + location.getLatitude() + " lon: " + location.getLongitude());
            Bundle bundle = location.getExtras();
            addMarkersToMap(location);
            if(bundle != null) {
                int errorCode = bundle.getInt(MyLocationStyle.ERROR_CODE);
                String errorInfo = bundle.getString(MyLocationStyle.ERROR_INFO);
                // 定位类型，可能为GPS WIFI等，具体可以参考官网的定位SDK介绍
                int locationType = bundle.getInt(MyLocationStyle.LOCATION_TYPE);

                /*
                errorCode
                errorInfo
                locationType
                */
                Log.e("amap", "定位信息， code: " + errorCode + " errorInfo: " + errorInfo + " locationType: " + locationType );
            } else {
                Log.e("amap", "定位信息， bundle is null ");

            }

        } else {
            Log.e("amap", "定位失败");
        }
    }

    private void addMarkersToMap(Location location){
        final LatLng mLatLng=new LatLng(location.getLatitude()+0.01,location.getLongitude()+0.01);
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.draggable(false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.hongb_icon)))
                .position(mLatLng);
        aMap.addMarker(markerOptions);

    }
//自定义地图
    private void setMapCustomStyleFile(Context context) {
        //草色青mystyle_sdk_1508855865_0100.data

        //马卡龙mystyle_sdk_1508855841_0100.data

        //标准mystyle_sdk_1508856206_0100.data
        String styleName = "mystyle_sdk_1508856206_0100.data";



        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        String filePath = null;
        try {
            inputStream = context.getAssets().open(styleName);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            filePath = context.getFilesDir().getAbsolutePath();
            File file = new File(filePath + "/" + styleName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            outputStream.write(b);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();

                if (outputStream != null)
                    outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        aMap.setCustomMapStylePath(filePath + "/" + styleName);

        aMap.showMapText(true);

    }

    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (aMap != null) {
            jumpPoint(marker);
        }
        Toast.makeText(getContext(), "您点击了Marker", Toast.LENGTH_LONG).show();
        return true;
    }
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
    }
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }
    /**
     * marker点击时跳动一下
     */
    public void jumpPoint(final Marker marker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        final LatLng markerLatlng = marker.getPosition();
        Point markerPoint = proj.toScreenLocation(markerLatlng);
        markerPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(markerPoint);
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * markerLatlng.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * markerLatlng.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }
}