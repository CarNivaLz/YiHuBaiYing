package android.example.com.yihubaiying.adapter;

import android.example.com.yihubaiying.R;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Thingvellir on 2017/10/31.
 */

public class RankAdapter extends BaseQuickAdapter<RankItem,BaseViewHolder> {


    public RankAdapter(int layoutResId,List data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RankItem item) {
        helper.setText(R.id.rank_num,item.getRanknum()).setImageResource(R.id.rank_user,item.getUserImageId()).setText(R.id.rank_nickname,item.getNickName());
        helper.setText(R.id.rank_decrib,item.getDescription()).setImageResource(R.id.rank_arrow,item.getArrowImageId()).setImageResource(R.id.rank_diamond,item.getDiamondImageId());

    }
}
