package com.lightheart.sdklib;

import com.blankj.utilcode.util.LogUtils;
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
 * @date 2018/12/20
 * @desc
 */
public class RetrofitManager {

    private Retrofit retrofit;
    private static String baseUrl;

    private RetrofitManager() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }

    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static RetrofitManager getInstance(IBaseUrl iBaseUrl) {
        baseUrl = iBaseUrl.getBaseUrl();
        return ManagerHolder.get();
    }

    public static OkHttpClient getOkHttpClient() {
        return ManagerHolder.getClient();
    }

    private static class ManagerHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
        private static OkHttpClient okHttpClient;

        private synchronized static RetrofitManager get() {
            if (okHttpClient==null) {
                initClient();
            }
            return INSTANCE;
        }

        private static void initClient() {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            okHttpClient = builder.connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .followRedirects(true)
                    .retryOnConnectionFailure(true)
                    .addNetworkInterceptor(new HeaderInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor(LogUtils::e)
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .cookieJar(CookieJar.NO_COOKIES)//TODO cookie持久化以及证书验证，是否需要改进？
                    .authenticator(Authenticator.NONE)
                    .build();
        }


        private synchronized static OkHttpClient getClient() {
            if (okHttpClient==null) {
                initClient();
            }
            return okHttpClient;
        }
    }
}
