package android.example.com.yihubaiying;

import android.Manifest;
import android.example.com.yihubaiying.fragment.Fragment_HongBaoMap;
import android.example.com.yihubaiying.fragment.Fragment_TongXunLu;
import android.example.com.yihubaiying.fragment.Fragment_WoDe;
import android.example.com.yihubaiying.fragment.Fragment_YouYiSi;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int RC_CAMERA = 2333;
    private RadioGroup radioGroup;
    private RadioButton radio_hongBaoMap,radio_tongXunLu,radio_woDe,radio_youYiSi;

    private Fragment fragment_HongBaoMap,fragment_TongXunLu,fragment_WoDe,fragment_YouYiSi;
//    private List<Fragment> fragmentList;

    private FrameLayout frameLayout;

    public static Random r=new Random(1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        initView();
    }



    private void initView(){

        FloatingActionButton floatingActionButton= (FloatingActionButton) findViewById(R.id.ibHome);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"hh",Toast.LENGTH_SHORT).show();
            }
        });


        frameLayout=(FrameLayout)findViewById(R.id.framelayout);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);

        radio_hongBaoMap=(RadioButton)findViewById(R.id.radio_hongbaomap);
        radio_youYiSi=(RadioButton) findViewById(R.id.radio_youyisi);
        radio_tongXunLu=(RadioButton)findViewById(R.id.radio_tongxunlu);
        radio_woDe=(RadioButton)findViewById(R.id.radio_wode);

        fragment_HongBaoMap=new Fragment_HongBaoMap();
        fragment_YouYiSi=new Fragment_YouYiSi();
        fragment_TongXunLu=new Fragment_TongXunLu();
        fragment_WoDe=new Fragment_WoDe();

//        fragmentList=new ArrayList<>();
//        fragmentList.add(fragment_HongBaoMap);
//        fragmentList.add(fragment_YouYiSi);
//        fragmentList.add(fragment_TongXunLu);
//        fragmentList.add(fragment_WoDe);

        //设置RadioGroup开始时设置的按钮，设置第一个按钮为默认值
        radioGroup.check(R.id.radio_hongbaomap);
        //监听
        radio_hongBaoMap.setOnClickListener(this);
        radio_youYiSi.setOnClickListener(this);
        radio_tongXunLu.setOnClickListener(this);
        radio_woDe.setOnClickListener(this);
//初始时向容器中添加第一个Fragment对象
        addFragment(fragment_HongBaoMap);
    }
    //向Activity中添加Fragment的方法
    private void addFragment(Fragment fragment){
        //获得Fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        //使用管理器开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //使用事务替换Fragment容器中Fragment对象
        fragmentTransaction.replace(R.id.framelayout,fragment);
        //提交事务，否则事务不生效
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View v) {
        //我们根据参数的id区别不同按钮
        //不同按钮对应着不同的Fragment对象页面
        switch (v.getId()){
            case R.id.radio_hongbaomap:
                fragment_HongBaoMap=new Fragment_HongBaoMap();
                addFragment(fragment_HongBaoMap);
                break;
            case R.id.radio_youyisi:
                addFragment(fragment_YouYiSi);
                break;
            case R.id.radio_tongxunlu:
                addFragment(fragment_TongXunLu);

                break;
            case R.id.radio_wode:
                addFragment(fragment_WoDe);
                break;

        }
    }
    @Override
    public void finish() {
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        viewGroup.removeAllViews();
        super.finish();
    }

}
