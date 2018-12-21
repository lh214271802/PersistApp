package com.lightheart.sdklib;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author LiaoHui
 * @date 2018/12/21
 * @desc
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url();
        //这里可以添加查询参数和header
        httpUrl = httpUrl.newBuilder().addQueryParameter("aaa", "dddd").build();
        request = request.newBuilder().headers(Headers.of(new HashMap<String, String>() {{
            put("token", "xxxx");
            put("username", "111");
        }})).url(httpUrl).build();
        return chain.proceed(request);
    }
}
