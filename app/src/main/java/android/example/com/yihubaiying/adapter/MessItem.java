package android.example.com.yihubaiying.adapter;

import android.content.Context;
import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_DongTai;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_HongBao;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_PaiHangBang;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_RenWu;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_XiaoXi;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

/**
 * Created by Thingvellir on 2017/11/2.
 */

public class MessItem {


    public static class MyInfoWinAdapter implements AMap.InfoWindowAdapter {
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
    //        navigation = (LinearLayout) view.findViewById(R.id.hongbao);
    //        call = (LinearLayout) view.findViewById(R.id.cancell);
            nameTV = (TextView) view.findViewById(R.id.name);
            addrTV = (TextView) view.findViewById(R.id.addr);

            nameTV.setText(String.format(context.getString(R.string.agent_title),agentName));
            addrTV.setText(String.format(context.getString(R.string.agent_message),snippet));

    //        navigation.setOnClickListener(this);
    //        call.setOnClickListener(this);
            return view;
        }

    //
    //    @Override
    //    public void onClick(View v) {
    //        int id = v.getId();
    //        switch (id){
    //            case R.id.hongbao:  //点击导航
    //
    //
    //                    break;
    //
    //            case R.id.cancell:  //点击打电话
    //
    //               break;
    //        }
    //    }

    }

    /**
     * Created by carnivalnian on 2017/10/21.
     */

    public static class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitle=new String[]{"红包","消息","任务" ,"动态","排行榜" };

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position){
            if (position==0){return new Fragment_HongBao();}
            if (position==1){return new Fragment_XiaoXi();}
            else if (position==2){return new Fragment_RenWu();}
            else if (position==3){return new Fragment_DongTai();}
            else if (position==4){return new Fragment_PaiHangBang();}
            return new Fragment_HongBao();
        }

        @Override
        public int getCount(){return mTitle.length;}

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }

    }
}
