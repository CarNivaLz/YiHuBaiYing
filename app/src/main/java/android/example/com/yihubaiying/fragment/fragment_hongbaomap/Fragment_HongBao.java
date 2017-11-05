package android.example.com.yihubaiying.fragment.fragment_hongbaomap;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.activity.HongBaoActivity;
import android.example.com.yihubaiying.adapter.MyInfoWinAdapter;
import android.example.com.yihubaiying.enity.HongBao;
import android.example.com.yihubaiying.loader.GlideImageLoader;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap.CancelableCallback;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import static android.example.com.yihubaiying.MainActivity.r;
/**
 * Created by carnivalnian on 2017/10/21.
 */

public  class Fragment_HongBao extends LazyFragment implements AMap.OnMyLocationChangeListener,
        AMap.OnMarkerClickListener,
        View.OnClickListener,
        AMap.OnMapClickListener,
        CancelableCallback,
        AMap.OnInfoWindowClickListener,
        AMap.OnMapLongClickListener{

    private static final int STROKE_COLOR = Color.argb(180, 3, 145, 255);
    private static final int FILL_COLOR = Color.argb(10, 0, 0, 180);
    public static boolean isInit = false;
    private boolean isAdded=false;
    private Banner banner;
    private AMap aMap;
    private TextureMapView mapView;
    private LocationSource locationSource;
    private UiSettings uiSettings;
    private MyLocationStyle myLocationStyle;
    private Bitmap mBitmap;

    private TextView numHongbao;
    private ImageButton location_btn;
    private MyInfoWinAdapter adapter;
    private ArrayList<HongBao>hongBaoArrayList;
    private LatLng mineLatLng;
    private View markerView;
    private Marker markerLocal;


    private Circle circle;
    private LatLng hongbaoLatLng;

    private Marker sendMarker;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_hongbao,container,false);
        isInit=true;
        mapView=(TextureMapView)view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        Log.e("fragment_1","onCreate");
        AndPermission.with(this)
                .requestCode(101)
                .permission(
                        // 申请多个权限组方式：
                        Permission.LOCATION,
                        Permission.STORAGE
                )
                .callback(listener)
                .start();
        initView(view);
        setUpMap();
        return view;
    }


    private void initView(View view) {


/*        if (aMap == null) {
            aMap = mapView.getMap();
    }else{
            aMap.clear();
            markerLocal=null;
            aMap=mapView.getMap();
         isAdded=false;
         }*/
        initBanner(view);
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        markerView=inflater.inflate(R.layout.marker_redvelet,null);
        numHongbao =(TextView)markerView.findViewById(R.id.num_hongbao);

        location_btn=(ImageButton) view.findViewById(R.id.location_bt);


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
        aMap = mapView.getMap();

//地图自定义主题
        setMapCustomStyleFile(getContext());
        aMap.setMapCustomEnable(true);
//入果点击事件没反应看看是否监听器初始化了
        adapter=new MyInfoWinAdapter(getContext());
        aMap.setInfoWindowAdapter(adapter);
//监听
        aMap.setOnMyLocationChangeListener(this);
        aMap.setOnMapClickListener(this);
        aMap.setOnMarkerClickListener(this);
        location_btn.setOnClickListener(this);
        aMap.setOnInfoWindowClickListener(this);
//视角
        aMap.setMinZoomLevel(15);
        aMap.setMaxZoomLevel(17);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));

        //定位蓝点样式，定位模式
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.gps_point));
        myLocationStyle.strokeColor(Color.argb(1, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(1, 0, 0, 0));

        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);

        //地图控件
        uiSettings=aMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setScaleControlsEnabled(false);
        uiSettings.setZoomControlsEnabled(false);
        //启动

        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationStyle(myLocationStyle);

    }

    @Override
    protected void lazyLoad() {

        if (null != aMap) {
            //每次重新加载地图前，清除数据
            aMap.clear();
            markerLocal = null;
        isAdded=false;
        }
        setUpMap();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        aMap.reloadMap();
        Log.e("fragment_1","onResume");
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        aMap.reloadMap();
        Log.e("fragment_1","onPause");
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
        Log.e("fragment_1","onDestroyView");
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("fragment_1","onDestroy");
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
    private ArrayList<String> titleList=new ArrayList<>();
    private void initTitleList(){

        titleList.add("商家 中海国际");
        titleList.add("商家 川西坝子");
        titleList.add("商家 永辉超市");
        titleList.add("用户 王三");
        titleList.add("用户 胡一菲");
        titleList.add("用户 飞翔的荷兰豆");
        titleList.add("用户 lypeer");
        titleList.add("商家 安杰电脑维修");
        titleList.add("用户 电子科大杨伟豪");
        titleList.add("用户 李杰钰");
        titleList.add("用户 杨廷飞");
        titleList.add("商家 自然美理发");
        titleList.add("商家 中海国际");
        titleList.add("商家 链家");
        titleList.add("商家 KFC");
        titleList.add("商家 快捷酒店");

    }



    public void initHongbaoMarker(Location location){
        if (aMap != null && location != null) {
            initTitleList();

            mBitmap=convertViewToBitmap(markerView);
            for (int i=0;i<16;i++) {
                LatLng latLng=new LatLng(location.getLatitude() + 0.001 * (r.nextInt(10) - 5), location.getLongitude() + 0.001 * (r.nextInt(10) - 5));
                aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                        .position(latLng)
                        .title(titleList.get(i))
                        .snippet("电子科技大学附近锦绣花园二期开盘了！查看详细信息可以领取红包。")
                        .icon(BitmapDescriptorFactory.fromBitmap(changeBitmapSize(mBitmap))));
            }
        }
    }


    private Bitmap changeBitmapSize(Bitmap bitmap) {
        numHongbao.setText("88");
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
     * 对marker标注点点击响应事件
     */
    @Override
    public boolean onMarkerClick( Marker marker) {
        if (aMap != null) {
            if(circle!=null) {
                circle.remove();
            }
            if (!marker.getPosition().equals(mineLatLng)) {

                markerLocal = marker;
                changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        markerLocal.getPosition(), 17, 0, 0)), this);
            }
        }
        //返回 “false”，除定义的操作之外，默认操作也将会被执行（如果有infowindow会调用方法显示出来）
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.location_bt) {
            if (markerLocal != null) {
                markerLocal.hideInfoWindow();
                markerLocal = null;
            }
            changeCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                    mineLatLng, 17, 0, 0)),this);

        }

    }
    @Override
    public void onMapClick(LatLng point){
        //点击地图上没marker 的地方，隐藏inforwindow
        banner.setVisibility(View.GONE);
        if (markerLocal != null) {
            circle.remove();
            markerLocal.hideInfoWindow();
            markerLocal=null;
            //隐藏篮圈 marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_selected));
            //隐藏篮圈 marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker_selected));
        }

    }
    @Override
    public void onMapLongClick(LatLng point){
       sendMarker= aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                .position(point)
                .title("一呼百应 发布红包")
                .snippet("您是否要在此处发布红包？编辑发布详细信息请点击窗口")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.poi_marker_pressed)));

    }

    @Override
   public void onInfoWindowClick(Marker marker){
        if(marker == sendMarker){
            sendMarker.hideInfoWindow();
            sendMarker.remove();

        }else {
        marker.hideInfoWindow();
        circle.remove();
        float distance = AMapUtils.calculateLineDistance(mineLatLng,marker.getPosition());
        if(distance>300){
            Toast.makeText(getContext(),"距离太远，您无法领取红包",Toast.LENGTH_SHORT).show();
        }else {
            showDialog();
        }
        }
    }

    /**
     * 地图动画效果终止回调方法
     */
    @Override
    public void onCancel() {

    }


    private void showDialog() {
        Dialog dialog=new Dialog(getContext(),R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_default);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
//完成回掉

    @Override
    public void onFinish() {

        markerLocal.showInfoWindow();

        circle=aMap.addCircle(new CircleOptions()
                .center(markerLocal.getPosition())
                .strokeColor(STROKE_COLOR)
                .fillColor(FILL_COLOR)
                .strokeWidth(2f)
                .radius(300)
                .visible(true));
    }

    private void changeCamera(CameraUpdate update, CancelableCallback callback) {
            aMap.animateCamera(update, 200, callback);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if(AndPermission.hasPermission( getContext(),Permission.LOCATION)&&AndPermission.hasPermission( getContext(),Permission.STORAGE)) {
                if (requestCode == 200) {

                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == 200) {

            }
        }
    };

}