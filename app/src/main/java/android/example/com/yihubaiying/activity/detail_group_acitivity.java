package android.example.com.yihubaiying.activity;

import android.example.com.yihubaiying.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class detail_group_acitivity extends AppCompatActivity {
    private GridView gridView;
    private List<Map<String,Object>>data_list;
    private SimpleAdapter sim_adapter;
    private int[] icon={R.drawable.user_two, R.drawable.user_one, R.drawable.user_three, R.drawable.user_five, R.mipmap.icon_add, R.drawable.user_six};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_group);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gridView= (GridView) findViewById(R.id.gridview);
        data_list=new ArrayList<Map<String,Object>>();
        getData();
        String [] from={"image"};
        int [] to={R.id.image};
        sim_adapter=new SimpleAdapter(this,data_list, R.layout.group_item,from,to);
        gridView.setAdapter(sim_adapter);

    }

    private List<Map<String, Object>> getData() {
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            data_list.add(map);
        }
        return data_list;
    }
}
