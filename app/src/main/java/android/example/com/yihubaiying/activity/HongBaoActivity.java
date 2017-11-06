package android.example.com.yihubaiying.activity;

import android.content.Intent;
import android.example.com.yihubaiying.R;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by carnivalnian on 2017/10/31.
 */

public class HongBaoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(android.example.com.yihubaiying.R.layout.hongbao_content);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        random=(Button) findViewById(R.id.random_get);


    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.random_get:
                startActivity(new Intent(this,RandomHongbaoActivity.class));
        }
    }
}
