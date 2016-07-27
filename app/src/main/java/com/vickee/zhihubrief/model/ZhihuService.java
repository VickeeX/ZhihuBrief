package com.vickee.zhihubrief.model;

import com.vickee.zhihubrief.entity.NewsContentResult;
import com.vickee.zhihubrief.entity.NewsLatestResult;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Vickee on 2016/7/27.
 */
public interface ZhihuService {
    @GET("/api/4/news/latest")
    Call<NewsLatestResult> getLatestNews();

    @GET("/api/4/news/")
    Call<NewsContentResult> getStoryDetail();
}
