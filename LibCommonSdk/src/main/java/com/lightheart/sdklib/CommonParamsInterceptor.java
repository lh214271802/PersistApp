package com.lightheart.sdklib;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;

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
