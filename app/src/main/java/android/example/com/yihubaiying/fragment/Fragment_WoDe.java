package android.example.com.yihubaiying.fragment;

import android.content.Intent;
import android.example.com.yihubaiying.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by carnivalnian on 2017/10/21.
 */


public class Fragment_WoDe extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.adv_layout,container,false);
        initView(view);
        return view;
    }



    private void initView(View view) {

    }


    @Override
    public void onClick(View v) {

    }
}
