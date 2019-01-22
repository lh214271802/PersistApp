package com.lightheart.sdklib.di.module;

import android.app.Application;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;
import com.lightheart.sdklib.di.BaseApplication;
import dagger.Module;
import dagger.Provides;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Module
public class NetModule {


    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    CommonParamsInterceptor provideCommonInterceptor() {
        return new CommonParamsInterceptor();
    }


    /**
     * 添加公共参数
     */
    public class CommonParamsInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            // 添加新的参数
      /*      HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .addQueryParameter("package_name", "com.lightheart.persistapp")
                    .addQueryParameter("version_code", "1")
                    .addQueryParameter("version_name", "1.0")
                    .addQueryParameter("sign", "qwertyuiiopasdfghjklzxcvbnm")
                    .addQueryParameter("platform", "android");*/
            // 新的请求
            Request newRequest = oldRequest.newBuilder()
                    .headers(Headers.of(new HashMap<String, String>() {{
                        put("token", "xxxx");
                        put("username", "111");
                        put("IMEI", "111");
                        put("IMSI", "111");
                        put("version", "111");
                        put("deviceId", "111");
                        put("platform", "android");
                        put("package_name", "com.lightheart.persistapp");
                        put("version_code", "1");
                        put("version_name", "1.0");
                        put("sign", "qwertyuiiopasdfghjklzxcvbnm");
                    }}))
                    .method(oldRequest.method(), oldRequest.body())
//                    .url(authorizedUrlBuilder.build())
                    .build();

            return chain.proceed(newRequest);
        }
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(BaseApplication context, CommonParamsInterceptor commonInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .cache(new Cache(new File(context.getFilesDir(), "HttpCache"), 1024 * 1024 * 10))
                .addInterceptor(commonInterceptor)
                .addInterceptor(new HttpLoggingInterceptor(LogUtils::e).setLevel(HttpLoggingInterceptor.Level.BODY))
                .cookieJar(CookieJar.NO_COOKIES)//TODO cookie持久化以及证书验证，是否需要改进？
                .authenticator(Authenticator.NONE)
                .build();
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient mOkHttpClient, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
