package com.lightheart.sdklib.di.component;


import com.lightheart.sdklib.di.BaseApplication;
import com.lightheart.sdklib.di.module.*;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

/**
 * @author Lai
 * @time 2017/12/12 16:52
 * @describe describe
 */
@Component(modules = {
        AppModule.class,
        ActivitiesModule.class,
        FragmentsModule.class,
        NetModule.class,
        ApiModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class
})
@Singleton
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(BaseApplication application);

        @BindsInstance
        Builder baseUrl(String url);

        AppComponent build();
    }

    void inject(BaseApplication app);
}
