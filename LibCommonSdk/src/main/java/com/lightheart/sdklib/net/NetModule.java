package com.lightheart.sdklib.net;

import android.app.Application;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.lightheart.sdklib.CommonParamsInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetModule {


    OkHttpClient provideOkHttpClient(Application context, CommonParamsInterceptor commonInterceptor) {
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

    Retrofit provideRetrofit(Gson gson, OkHttpClient mOkHttpClient, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
