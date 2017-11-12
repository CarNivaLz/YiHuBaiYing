package android.example.com.yihubaiying.activity;

import android.content.Intent;
import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.utils.AppManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by carnivalnian on 2017/11/8.
 */

public class SelectHongbaoActivity extends BaseActivity implements View.OnClickListener{
    private Button daodian;
    private Button zhuanfa;
    private Button jiaqun;
    private Button diaocha;
    private ImageButton back_hongbaoselect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.hongbao_select);


        daodian=(Button)findViewById(R.id.daodian_get);
        zhuanfa=(Button)findViewById(R.id.zhuanfa_get);
        jiaqun=(Button)findViewById(R.id.jiaqun_get);
        diaocha=(Button)findViewById(R.id.diaocha_get);
        back_hongbaoselect=(ImageButton)findViewById(R.id.back_hongbaoselect);

        daodian.setOnClickListener(this);
        zhuanfa.setOnClickListener(this);
        jiaqun.setOnClickListener(this);
        diaocha.setOnClickListener(this);
        back_hongbaoselect.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.daodian_get:
                break;
            case R.id.zhuanfa_get:
                startActivity(new Intent(this,ZhuanFaActivity.class));
                break;
            case R.id.jiaqun_get:
                startActivity(new Intent(this,JiaQunActivity.class));
                break;
            case R.id.diaocha_get:
                break;
            case R.id.back_hongbaoselect:
                AppManager.getAppManager().finishActivity(this);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppManager.getAppManager().finishActivity(this);
    }
}
