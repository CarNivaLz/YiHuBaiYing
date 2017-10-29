package android.example.com.yihubaiying.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by carnivalnian on 2017/10/29.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View initV(@IdRes int id) {
        return findViewById(id);
    }

    public View initVclick(@IdRes int id) {
        View view = initV(id);
        view.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

    }



}
