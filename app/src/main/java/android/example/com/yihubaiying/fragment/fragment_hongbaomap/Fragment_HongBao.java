package android.example.com.yihubaiying.fragment.fragment_hongbaomap;

import android.content.Context;
import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.adapter.MyInfoWinAdapter;
import android.example.com.yihubaiying.enity.HongBao;
import android.example.com.yihubaiying.loader.GlideImageLoader;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
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
import java.util.Random;

/**
 * Created by carnivalnian on 2017/10/21.
 */

public  class Fragment_HongBao extends Fragment implements AMap.OnMyLocationChangeListener,
        AMap.OnMarkerClickListener,
        View.OnClickListener,
        AMap.OnMapClickListener{

    public static boolean isInit = false;
    private Banner banner;
    private AMap aMap;
    private TextureMapView mapView;
    private UiSettings uiSettings;
    private MyLocationStyle myLocationStyle;
    private Bitmap mBitmap;
    private int NUMBER=88;
    private boolean isAdded=false;
    private TextView numHongbao;
    private ImageButton location_btn;
    private MyInfoWinAdapter adapter;
    private ArrayList<HongBao>hongBaoArrayList;
    private LatLng mineLatLng;
    private View markerView;
private Marker markerLocal;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_hongbao,container,false);
        mapView=(TextureMapView)view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        location_btn=(ImageButton) view.findViewById(R.id.location_bt);
        initView(view);
        return view;
    }


    private void initView(View view) {
        initBanner(view);
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        markerView=inflater.inflate(R.layout.marker_redvelet,null);
        numHongbao =(TextView)markerView.findViewById(R.id.num_hongbao);
        if (aMap == null) {
            aMap = mapView.getMap();
    }else{
            aMap.clear();
            markerLocal=null;
            aMap=mapView.getMap();
         }
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

        setMapCustomStyleFile(getContext());

        adapter=new MyInfoWinAdapter(getContext());
        aMap.setInfoWindowAdapter(adapter);

        aMap.setMapCustomEnable(true);
        aMap.setOnMarkerClickListener(this);
        location_btn.setOnClickListener(this);
        //amp
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.gps_point));
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        //定位间隔
        //myLocationStyle.interval(2000);
        //transparent
        myLocationStyle.strokeColor(Color.argb(1, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(1, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);

        //ui控件

        uiSettings=aMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setScaleControlsEnabled(false);
        uiSettings.setZoomControlsEnabled(false);
        //启动
        aMap.setMyLocationEnabled(true);
        aMap.setMinZoomLevel(15);
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
        mapView.onDestroy();

    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

    }



    @Override
    public void onMyLocationChange(Location mylocation) {
        // 定位回调监听
        if(mylocation != null) {
            Log.e("amap", "onMyLocationChange 定位成功， lat: " + mylocation.getLatitude() + " lon: " + mylocation.getLongitude());
            Bundle bundle = mylocation.getExtras();
             mineLatLng=new LatLng(mylocation.getLatitude(),mylocation.getLongitude());
            if(isAdded==false) {
                initHongbaoMarker(mylocation);
               isAdded=true;
                Log.e("sucess to drawMarker","a");
            }
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


    private void setMapCustomStyleFile(Context context) {
        String styleName = "mystyle_sdk_1508946491_0100.data";
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
//   红包逻辑：

//    1、构造红包
//                拿到位置
//            构造随机红包位置信息
//    传入参数
//          {
//              bitmap方法：
//              view初始化
//              view转化成bitmap
//              改变bitmap大小
//          }
 //   添加红包
//    2、红包点击事件：
//        a、根据位置画范围圈  onmylocationchange回掉时调用 有isadded判断
//        b、显示infowindow
//                点击infowindow进入红包事件
//        c、点击屏幕取消显示



    public void initHongbaoMarker(Location location){

        Random r=new Random(System.currentTimeMillis());
        hongBaoArrayList=new ArrayList<>();
        for(int i=0;i<15;i++){
           LatLng hongbaoLatLng = new LatLng(location.getLatitude() + 0.0005 * (r.nextInt(10) - 5), location.getLongitude() + 0.0005 * (r.nextInt(10) - 5));
            HongBao mHongBao=new HongBao();
            mHongBao.setId(i);
            mHongBao.setTitle("中海国际");
            mHongBao.setNumber(i*6);
            mHongBao.setSnipped("中海国际电子科技大学清水河校区附近楼盘开盘啦！");
            mHongBao.setLatLng(hongbaoLatLng);
            hongBaoArrayList.add(mHongBao);
            numHongbao.setText("89");
            mBitmap=convertViewToBitmap(markerView);
            //
            drawMarkerOnMap(hongbaoLatLng,mBitmap,hongBaoArrayList.get(i).getTitle(),hongBaoArrayList.get(i).getSnipped());
            Log.e("sucess to drawMarker","b");
        }
    }

    private Bitmap changeBitmapSize(Bitmap bitmap) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //计算压缩的比率
        double scaleWidth=0.68;
        double scaleHeight=0.68;
        //获取想要缩放的matrix
        Matrix matrix = new Matrix();
        matrix.postScale((float) scaleWidth,(float) scaleHeight);
        //获取新的bitmap
        bitmap=Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
        bitmap.getWidth();
        bitmap.getHeight();
        Log.e("newWidth","newWidth"+bitmap.getWidth());
        Log.e("newHeight","newHeight"+bitmap.getHeight());
        return bitmap;
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
    /**
     * 画红包
     */
    private void drawMarkerOnMap(LatLng latLng, Bitmap markerIcon,String mtitle,String msnippet) {
        if (aMap != null && latLng != null) {
        for (int i=0;i<15;i++) {
             Marker marker =aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                        .position(latLng)
                        .title(mtitle)
                        .snippet(msnippet)
                        .icon(BitmapDescriptorFactory.fromBitmap(changeBitmapSize(markerIcon))));
                Log.e("sucess to drawMarker","c");
            }
        }
    }

    /**
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick( Marker marker) {
        if (aMap != null) {
            markerLocal=marker;
            //seticon显示蓝circle
        }
        //返回 “false”，除定义的操作之外，默认操作也将会被执行（如果有infowindow会调用方法显示出来）
        return false;
    }

 // 画红包范围蓝圈
    private void drawMarkerCircleOnMap(LatLng latLng){

        if(aMap!=null && latLng!=null){
            Marker marker=aMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.circle)));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.location_bt:
                    aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                            mineLatLng, 18,0,0)));
            break;
        }
    }
    @Override
    public void onMapClick(LatLng lPoint){
        //点击地图上没marker 的地方，隐藏inforwindow
        if (markerLocal != null) {
            markerLocal.hideInfoWindow();
            //隐藏篮圈 marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_selected));
            //隐藏篮圈 marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_selected));
        }
    }
}