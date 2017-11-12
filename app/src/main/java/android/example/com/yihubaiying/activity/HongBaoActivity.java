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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by carnivalnian on 2017/10/31.
 */

public class HongBaoActivity extends BaseActivity implements View.OnClickListener{
    private ImageButton back_hongbao;
    private Button random;
    private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hongbao_content);

        random=(Button) findViewById(R.id.random_get);
        random.setOnClickListener(this);
        checkBox=(CheckBox)findViewById(R.id.checkbox_hongbaocontent);
        back_hongbao=(ImageButton)findViewById(R.id.back_hongbao);
        back_hongbao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.random_get:
                if(checkBox.isChecked()) {
                    startActivity(new Intent(HongBaoActivity.this, RandomHongbaoActivity.class));
                    AppManager.getAppManager().finishActivity(this);
                }
                else {
                    Toast.makeText(this,"请您先阅读并同意相关红包领取规则",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back_hongbao:
                AppManager.getAppManager().finishActivity(this);
                break;
        }
    }

}
