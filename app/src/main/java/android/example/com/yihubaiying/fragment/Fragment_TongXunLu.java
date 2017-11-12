package android.example.com.yihubaiying.fragment;

import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.adapter.ContactAdapter;
import android.example.com.yihubaiying.adapter.ContactItem;
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


public class Fragment_TongXunLu extends Fragment implements View.OnClickListener{
        List<ContactItem> contentItemList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tongxunlu_layout,container,false);
        initView(view);
        initDatas();
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.contact_recycleview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ContactAdapter adapter=new ContactAdapter(R.layout.contact_layout,contentItemList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initDatas() {
        ContactItem item=new ContactItem(R.drawable.user_four,"龙的传人", R.drawable.tel_icon);
        ContactItem item1=new ContactItem(R.drawable.user_four,"龙的传人", R.drawable.tel_icon);
        ContactItem item2=new ContactItem(R.drawable.user_four,"龙的传人", R.drawable.tel_icon);
        ContactItem item3=new ContactItem(R.drawable.user_four,"龙的传人", R.drawable.tel_icon);
        contentItemList.add(item);
        contentItemList.add(item2);
        contentItemList.add(item3);


    }


    private void initView(View view) {

    }


    @Override
    public void onClick(View v) {

    }
}