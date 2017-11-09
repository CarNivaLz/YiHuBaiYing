package android.example.com.yihubaiying.fragment.fragment_hongbaomap;

import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.adapter.DongtaiAdapter;
import android.example.com.yihubaiying.adapter.DongtaiItem;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wx.goodview.GoodView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carnivalnian on 2017/10/21.
 */

public class Fragment_DongTai extends Fragment implements View.OnClickListener{
    private List<DongtaiItem> dongtaiItemList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_dongtai,container,false);
        initView(view);
        initDongtai();
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.dongtai_recycleview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DongtaiAdapter adapter=new DongtaiAdapter(R.layout.dontai_layout,dongtaiItemList);
        recyclerView.setAdapter(adapter);
        return view;

    }

    private void initDongtai() {
        for (int i=0;i<10;i++){
            DongtaiItem dongtaiItem=new DongtaiItem(R.drawable.user_nine,"追书首席打杂  lv.10","三小时前","[领取红包]我刚刚在这这里领取了大额的红包，赶快加入我把","我刚刚在这里领取了大额的红包。我就问你服不服。",R.drawable.good);
            dongtaiItemList.add(dongtaiItem);

        }

    }


    private void initView(View view) {

    }


    @Override
    public void onClick(View v) {

    }

}
