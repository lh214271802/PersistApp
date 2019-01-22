package com.lightheart.sdklib.di;

import android.content.Context;
import com.blankj.utilcode.util.Utils;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * @author LiaoHui
 * @date 2018/10/31
 * @desc
 */
public class BaseApplication extends DaggerApplication {
    private static BaseApplication app;

    @Override
    public void onCreate() {
        app = this;
        super.onCreate();
        Utils.init(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        return DaggerAppComponent.builder().application(this).baseUrl("i am jack").build();
        return null;
    }

    public static Context getApp() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
