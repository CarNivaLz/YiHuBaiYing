package android.example.com.yihubaiying.adapter;

import android.example.com.yihubaiying.R;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Thingvellir on 2017/11/2.
 */

public class MessAdapter extends BaseQuickAdapter<MessItem,BaseViewHolder> {

    public MessAdapter(int layoutResId,List data){
        super(layoutResId,data);
    }
    @Override
    protected void convert(BaseViewHolder helper, MessItem item) {
        helper.setImageResource(R.id.mess_user,item.getUserImageId()).setText(R.id.mess_title,item.getTitle());
        helper.setText(R.id.mess_content,item.getContent()).setText(R.id.mess_time,item.getTime());
        helper.setImageResource(R.id.mess_state,item.getStateImageId());

    }
}
