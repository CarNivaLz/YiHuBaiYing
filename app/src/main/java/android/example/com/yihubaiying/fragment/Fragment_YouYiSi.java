package android.example.com.yihubaiying.fragment;

import android.content.Intent;
import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.activity.Newsdetail_activity;
import android.example.com.yihubaiying.adapter.News;
import android.example.com.yihubaiying.adapter.NewsAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carnivalnian on 2017/10/21.
 */


public class Fragment_YouYiSi extends Fragment implements View.OnClickListener{
    List<News> newsItemList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.youyisi_layout,container,false);
        initView(view);

        initDatas();
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.youyisi_recycle);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter=new NewsAdapter(R.layout.news_layout,newsItemList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getContext(), "onItemChildClick" + position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(),Newsdetail_activity.class));
            }
        });

        return view;
    }

    private void initDatas() {
        News news1=new News("创纪录！中美两天签经贸大单2535亿美元","网易新闻","58971看过",R.drawable.jiang_icon);
        News news2=new News("创纪录！中美两天签经贸大单2535亿美元","网易新闻","58971看过",R.drawable.jiang_icon);
        News news3=new News("创纪录！中美两天签经贸大单2535亿美元","网易新闻","58971看过",R.drawable.jiang_icon);
        News news4=new News("创纪录！中美两天签经贸大单2535亿美元","网易新闻","58971看过",R.drawable.jiang_icon);
        newsItemList.add(news1);
        newsItemList.add(news2);
        newsItemList.add(news2);
        newsItemList.add(news2);


    }


    private void initView(View view) {

    }


    @Override
    public void onClick(View v) {

    }
}