package android.example.com.yihubaiying.activity;

import android.content.Intent;
import android.example.com.yihubaiying.MainActivity;
import android.example.com.yihubaiying.R;
import android.example.com.yihubaiying.utils.AppManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.curzbin.library.BottomDialog;
import me.curzbin.library.Item;
import me.curzbin.library.OnItemClickListener;

/**
 * Created by carnivalnian on 2017/11/10.
 */

public class ZhuanFaActivity extends BaseActivity implements View.OnClickListener {
    private EditText comments_txt;
    private Button zhuanfa_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_zhuanfa);


        comments_txt=(EditText)findViewById(R.id.comments);
        zhuanfa_btn=(Button)findViewById(R.id.zhuanfa_jump);
        zhuanfa_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.zhuanfa_jump){
            new BottomDialog(ZhuanFaActivity.this)
                    .title("分享到")
                    .orientation(BottomDialog.HORIZONTAL)
                    .inflateMenu(R.menu.menu_share, new OnItemClickListener() {
                        @Override
                        public void click(Item item) {
                            Toast.makeText(ZhuanFaActivity.this, getString(R.string.share_title) + item.getTitle() +"成功", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ZhuanFaActivity.this,RandomHongbaoActivity.class));
                            AppManager.getAppManager().finishActivity(ZhuanFaActivity.this);
                        }
                    })
                    .show();


        }
    }
}
