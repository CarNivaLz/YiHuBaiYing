package android.example.com.yihubaiying.adapter;

import android.example.com.yihubaiying.R;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Thingvellir on 2017/11/1.
 */

public class DongtaiAdapter extends BaseQuickAdapter<DongtaiItem,BaseViewHolder> {


    public DongtaiAdapter(int layoutResId,List data) {
        super(layoutResId,data);
    }

    protected void convert(BaseViewHolder helper, DongtaiItem item) {
        helper.setText(R.id.dongtai_miaoshu,item.getMiaoshu()).setText(R.id.dongtai_nickname,item.getNicheng()).setText(R.id.dongtai_time,item.getTime()).setImageResource(R.id.dongtai_image,item.getAdv_image()).setImageResource(R.id.dongtai_user,item.getImageId());

    }
}