package android.example.com.yihubaiying.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Thingvellir on 2017/11/1.
 */

public class TaskAdapter extends BaseQuickAdapter<TaskItem,BaseViewHolder> {


    public TaskAdapter(int layoutResId,List data) {
        super(layoutResId,data);
    }

    protected void convert(BaseViewHolder helper, TaskItem item) {


    }
}
