package android.example.com.yihubaiying.fragment.fragment_hongbaomap;

import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.adapter.RankAdapter;
import android.example.com.yihubaiying.adapter.RankItem;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carnivalnian on 2017/10/21.
 */

public class Fragment_PaiHangBang extends Fragment implements View.OnClickListener{
    private List<RankItem> rankItemList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_paihangbang,container,false);
        initRank();
//        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.recycleview);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        RankAdapter adapter=new RankAdapter(R.layout.rank_layout,rankItemList);
//        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initRank() {
        for (int i=0;i<5;i++){
            RankItem item=new RankItem(""+3, R.drawable.user_four,"嘻哈王", R.drawable.diamond_icon,"日红包:152.6元", R.drawable.up_icon);
            RankItem item1=new RankItem(""+4, R.drawable.user_one,"龙的传人", R.drawable.diamond_icon,"日红包:142.6元", R.drawable.down_icon);
            RankItem item2=new RankItem(""+5, R.drawable.user_four,"知乎", R.drawable.diamond_icon,"日红包:121.6元", R.drawable.down_icon);
            RankItem item3=new RankItem(""+6, R.drawable.user_four,"那时年少", R.drawable.diamond_icon,"日红包:111.6元", R.drawable.up_icon);
            rankItemList.add(item);
            rankItemList.add(item1);
            rankItemList.add(item2);
            rankItemList.add(item3);
        }
    }

    @Override
    public void onClick(View v) {

    }


}