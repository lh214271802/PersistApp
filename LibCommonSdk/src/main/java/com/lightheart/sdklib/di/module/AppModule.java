package com.lightheart.sdklib.di.module;


import android.app.Application;
import com.lightheart.sdklib.di.BaseApplication;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author Lai
 * @time 2017/12/11 11:43
 * @describe 将会提供Application 的context引用
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Application provideContext(BaseApplication application) {
        return application;
    }

    @Named("jk")
    @Provides
    @Singleton
    String provideName(String name) {
        return name;
    }
}
