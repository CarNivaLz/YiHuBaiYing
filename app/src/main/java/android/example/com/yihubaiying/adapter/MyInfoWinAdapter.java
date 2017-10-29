package android.example.com.yihubaiying.adapter;

/**
 * Created by carnivalnian on 2017/10/29.
 * 地图上自定义的infowindow的适配器
 */

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import android.content.Context;
import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.activity.BaseApplication;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_HongBao;
import android.example.com.yihubaiying.utils.NavigationUtils;
import android.example.com.yihubaiying.utils.PhoneCallUtils;
import android.support.annotation.NonNull;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.LinearLayout;
        import android.widget.TextView;


public class MyInfoWinAdapter implements AMap.InfoWindowAdapter, View.OnClickListener {
    private Context context;


    private LatLng latLng;
    private LinearLayout call;
    private LinearLayout navigation;
    private TextView nameTV;
    private String agentName;
    private TextView addrTV;
    private String snippet;

    public MyInfoWinAdapter(Context context){
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        initData(marker);
        View view = initView();
        return view;
    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    private void initData(Marker marker) {
        latLng = marker.getPosition();
        snippet = marker.getSnippet();
        agentName = marker.getTitle();
    }

    @NonNull
    private View initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.hongbao_infowindow, null);
        navigation = (LinearLayout) view.findViewById(R.id.navigation_LL);
        call = (LinearLayout) view.findViewById(R.id.call_LL);
        nameTV = (TextView) view.findViewById(R.id.name);
        addrTV = (TextView) view.findViewById(R.id.addr);

        nameTV.setText(String.format(context.getString(R.string.agent_title),agentName));
        addrTV.setText(String.format(context.getString(R.string.agent_message),snippet));

        navigation.setOnClickListener(this);
        call.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.navigation_LL:  //点击导航
                NavigationUtils.Navigation(latLng);
                break;

            case R.id.call_LL:  //点击打电话
                PhoneCallUtils.call("028-"); //TODO 处理电话号码
                break;
        }
    }

}

