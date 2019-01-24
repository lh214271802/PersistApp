package com.lightheart.sdklib;

import android.app.Application;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import okhttp3.Authenticator;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author LiaoHui
 * @date 2019/1/24
 * @desc
 */
public class NetUtil {

    private static Application mContext;
    private static Retrofit retrofit;

    public static void init(Application context) {
        mContext = context;
    }

    public static Gson getGson() {
        return Holder.gson;
    }

    public static OkHttpClient getOkHttpClient() {
        return Holder.mOkHttpClient;
    }

    public static Retrofit getRetrofit(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
        return retrofit;
    }

    public static <T> T getRequest(Class<T> tClass) {
        return retrofit.create(tClass);
    }


    private static class Holder {
        private static final Gson gson = new Gson();
        private static final CommonParamsInterceptor commonParamsInterceptor = new CommonParamsInterceptor();
        private static final OkHttpClient mOkHttpClient = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .addInterceptor(commonParamsInterceptor)
                .addInterceptor(new HttpLoggingInterceptor(LogUtils::e).setLevel(HttpLoggingInterceptor.Level.BODY))
                .cookieJar(CookieJar.NO_COOKIES)//TODO cookie持久化以及证书验证，是否需要改进？
                .authenticator(Authenticator.NONE)
                .build();
    }

}
