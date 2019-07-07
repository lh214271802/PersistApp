package com.lightheart.persistapp.api;

import com.lightheart.persistapp.ui.main.HotMovieBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author LiaoHui
 * @date 2019/1/25
 * @desc
 */
public interface AppApi {
    String HOST_NAME = "Https://api.douban.com/";

    /**
     * 获取豆瓣电影top250
     *
     * @param start 从多少开始，如从"0"开始
     * @param count 一次请求的数目，如"10"条，最多100
     */
    @GET("v2/movie/top250")
    Observable<HotMovieBean> getMovieTop250(@Query("start") int start, @Query("count") int count);
}
