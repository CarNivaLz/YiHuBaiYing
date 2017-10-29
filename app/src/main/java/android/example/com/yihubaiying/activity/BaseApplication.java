package android.example.com.yihubaiying.activity;

import android.app.Application;

/**
 * Created by carnivalnian on 2017/10/29.
 */

public class BaseApplication extends Application {

    private static BaseApplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

    }

    public static BaseApplication getIntance() {
        return mApplication;
    }

}

