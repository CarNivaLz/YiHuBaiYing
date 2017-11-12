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
        helper.setImageResource(R.id.dongttai_image,item.getUserImageId()).setText(R.id.dongtai_nickname,item.getNickname()).setText(R.id.dongtai_time,item.getTime()).setText(R.id.dongtai_title,item.getTitle()).setText(R.id.dongtai_content,item.getContent()).setImageResource(R.id.comment_good,item.getGoodImage());

    }
}