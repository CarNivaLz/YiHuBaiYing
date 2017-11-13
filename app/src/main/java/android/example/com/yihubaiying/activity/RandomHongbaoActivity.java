package android.example.com.yihubaiying.activity;

import android.content.Intent;
import android.example.com.yihubaiying.R;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by carnivalnian on 2017/11/6.
 */

public class RandomHongbaoActivity extends BaseActivity implements View.OnClickListener {
private boolean hasMoney=false;

    private Handler mViewHandler;
    private HandlerThread thread;
    private TigerMachineView mTigerView1, mTigerView2, mTigerView3,mTigerView4,mTigerView5;
    private ArrayList<String> money=new ArrayList<>();
    private String myMoney;
    private TextView textView;

    private Handler mHandler=new Handler();
    private Button startA;
    private ImageButton back_hongbaorandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hongbao_random);

        back_hongbaorandom=(ImageButton)findViewById(R.id.back_hongbaorandom);
        startA=(Button)findViewById(R.id.random_getget);
        textView=(TextView)findViewById(R.id.myMoney);
        mTigerView1 = (TigerMachineView) findViewById(R.id.t1);
        mTigerView2 = (TigerMachineView) findViewById(R.id.t2);
        mTigerView3 = (TigerMachineView) findViewById(R.id.t3);
        mTigerView4 = (TigerMachineView) findViewById(R.id.t4);
        mTigerView5 = (TigerMachineView) findViewById(R.id.t5);
        thread = new HandlerThread("MyHandlerThread");
        thread.start();
        mViewHandler = new Handler(thread.getLooper());
        mViewHandler.post(mTask);
        startA.setOnClickListener(this);
        back_hongbaorandom.setOnClickListener(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewHandler.removeCallbacks(mTask);
        AppManager.getAppManager().finishActivity(this);
        AppManager.getAppManager().finishActivity(ZhuanFaActivity.class);
        AppManager.getAppManager().finishActivity(JiaQunActivity.class);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.random_getget:

                    startActivity(new Intent(this, SelectHongbaoActivity.class));
                    AppManager.getAppManager().finishActivity(this);
                break;
            case R.id.back_hongbaorandom:
                AppManager.getAppManager().finishActivity(this);
                break;
        }
    }


    private Runnable mTask = new Runnable() {

        private void doIt(TigerMachineView v, int time) {
            Random r = new Random();
            int idx;
            idx=r.nextInt(10);
            v.scrollTo(idx,  time);
            String change=idx+"";
            money.add(change);
        }

        @Override
        public void run() {

            int time = 1000;
            try {
                Thread.sleep(time);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doIt(mTigerView5, time);
            try {
                Thread.sleep(time);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doIt(mTigerView4, time);
            try {
                Thread.sleep(time);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doIt(mTigerView3, time);
            try {
                Thread.sleep(time);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doIt(mTigerView2, time);
            try {
                Thread.sleep(time);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            doIt(mTigerView1, time);
            try {
                Thread.sleep(time);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    myMoney="您获得了"+money.get(4)+money.get(3)+money.get(2)+"."+money.get(1)+money.get(0)+" 元";
                    textView.setText(myMoney);
                    textView.setVisibility(View.VISIBLE);
                }
            });

            mViewHandler.removeCallbacks(mTask);

        }
    };


}
