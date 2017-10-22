package android.example.com.yihubaiying.fragment.fragment_hongbaomap;

import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.loader.GlideImageLoader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by carnivalnian on 2017/10/21.
 */

public  class Fragment_HongBao extends Fragment implements View.OnClickListener{

private Banner banner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_hongbao,container,false);
        initView(view);



        return view;
    }



    private void initView(View view) {
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

    @Override
    public void onClick(View v) {

    }
}