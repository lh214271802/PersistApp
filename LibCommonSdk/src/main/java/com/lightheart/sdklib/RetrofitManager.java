package com.lightheart.sdklib;

import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author LiaoHui
 * @date 2018/12/20
 * @desc
 */
public class RetrofitManager {

    private RetrofitManager() {
        new Retrofit.Builder()
                .baseUrl(ManagerHolder.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(ManagerHolder.getClient())
                .build();

    }

    public static RetrofitManager getInstance(IBaseUrl iBaseUrl) {
        return ManagerHolder.get(iBaseUrl);
    }

    private static class ManagerHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
        private static String baseUrl;
        private static OkHttpClient okHttpClient;

        private synchronized static RetrofitManager get(IBaseUrl iBaseUrl) {
            baseUrl = iBaseUrl.getBaseUrl();
            initClient();
            return INSTANCE;
        }

        private static void initClient() {
            if (okHttpClient == null) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                return null;
                            }
                        })
                        .addNetworkInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                return null;
                            }
                        })
                        .cookieJar(new CookieJar() {
                            @Override
                            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                            }

                            @Override
                            public List<Cookie> loadForRequest(HttpUrl url) {
                                return null;
                            }
                        })
                        .build();
            }
        }

        private synchronized static OkHttpClient getClient() {
            if (okHttpClient == null) {
                initClient();
            }
            return okHttpClient;
        }
    }

}
