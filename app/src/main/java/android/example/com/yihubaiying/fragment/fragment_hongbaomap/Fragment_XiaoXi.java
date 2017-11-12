package android.example.com.yihubaiying.fragment.fragment_hongbaomap;

import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.adapter.DongtaiItem;
import android.example.com.yihubaiying.adapter.MessAdapter;
import android.example.com.yihubaiying.adapter.MessItem;
import android.example.com.yihubaiying.adapter.Message;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carnivalnian on 2017/10/21.
 */

public class Fragment_XiaoXi extends  Fragment implements View.OnClickListener{
    private List<Message> MessItemList=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_xiaoxi,container,false);
        initView(view);
        initDatas();
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.message_recycle);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        MessAdapter adapter=new MessAdapter(R.layout.message_layout,MessItemList);
        recyclerView.setAdapter(adapter);






        return view;
    }

    private void initDatas() {
        Message item=new Message(R.drawable.user_nine,"旅行社小张","嗯嗯好的我知道了，我马上把位置发过去。","15:28",R.drawable.red_icon);
        MessItemList.add(item);

    }


    private void initView(View view) {

    }


    @Override
    public void onClick(View v) {

    }
}
