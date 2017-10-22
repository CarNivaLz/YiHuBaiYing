package android.example.com.yihubaiying.adapter;

import android.example.com.yihubaiying.fragment.Fragment_HongBaoMap;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_DongTai;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_HongBao;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_PaiHangBang;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_RenWu;
import android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_XiaoXi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by carnivalnian on 2017/10/21.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

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
