package android.example.com.yihubaiying.activity;
import android.content.Intent;
import android.example.com.yihubaiying.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.mcxtzhang.captchalib.SwipeCaptchaView;


/**
 * Created by carnivalnian on 2017/11/10.
 */

public class JiaQunActivity extends BaseActivity implements View.OnClickListener{
    private SwipeCaptchaView mSwipeCaptchaView;
    private SeekBar mSeekBar;
    private Button jiaqun_btn;
    private CheckBox checkBox;
    private ImageButton back_hongbaojiaqun;
    private boolean checkResult =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_jiaqun);

        mSwipeCaptchaView = (SwipeCaptchaView) findViewById(R.id.swipeCaptchaView);
        jiaqun_btn=(Button)findViewById(R.id.jiaqun_jump);
        jiaqun_btn.setOnClickListener(this);
        mSeekBar = (SeekBar) findViewById(R.id.dragBar);
        checkBox=(CheckBox)findViewById(R.id.checkbox_jiaquncontent);
        back_hongbaojiaqun=(ImageButton)findViewById(R.id.back_hongbaojiaqun);
        back_hongbaojiaqun.setOnClickListener(this);
        checkResult=false;
        mSwipeCaptchaView.setOnCaptchaMatchCallback(new SwipeCaptchaView.OnCaptchaMatchCallback() {
            @Override
            public void matchSuccess(SwipeCaptchaView swipeCaptchaView) {
                Toast.makeText(JiaQunActivity.this, "恭喜您，验证成功，快去点击领取红包吧", Toast.LENGTH_SHORT).show();
                //swipeCaptcha.createCaptcha();
                mSeekBar.setEnabled(false);
                checkResult=true;
            }

            @Override
            public void matchFailed(SwipeCaptchaView swipeCaptchaView) {
                Log.d("zxt", "matchFailed() called with: swipeCaptchaView = [" + swipeCaptchaView + "]");
                Toast.makeText(JiaQunActivity.this, "再加把劲试试，红包就在前方", Toast.LENGTH_SHORT).show();
                mSwipeCaptchaView.createCaptcha();
                mSeekBar.setEnabled(true);
                mSeekBar.setProgress(0);
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSwipeCaptchaView.setCurrentSwipeValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //随便放这里是因为控件
                mSeekBar.setMax(mSwipeCaptchaView.getMaxSwipeValue());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("zxt", "onStopTrackingTouch() called with: seekBar = [" + seekBar + "]");
                mSwipeCaptchaView.matchCaptcha();
            }
        });

        Glide.with(this)
                .load(R.drawable.pic11)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mSwipeCaptchaView.setImageBitmap(resource);
                        mSwipeCaptchaView.createCaptcha();
                    }
                });

    }
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.jiaqun_jump){
            if(checkResult){
                if(checkBox.isChecked()){
                    AppManager.getAppManager().finishActivity();
                    Toast.makeText(JiaQunActivity.this,"加群成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"请您先阅读并同意《服务协议》和《隐私条款》",Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(this,"滑动验证通过后才能领取红包哈",Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId()==R.id.back_hongbaojiaqun){
            AppManager.getAppManager().finishActivity(this);
        }
    }

}
