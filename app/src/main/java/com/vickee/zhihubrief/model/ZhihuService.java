package com.vickee.zhihubrief.model;

import com.vickee.zhihubrief.entity.NewsContentResult;
import com.vickee.zhihubrief.entity.NewsLatestResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Vickee on 2016/7/27.
 */
public interface ZhihuService {
    @GET("/api/4/news/latest")
    Observable<NewsLatestResult> getLatestNews();

    @GET("/api/4/news/{id}")
    Observable<NewsContentResult> getStoryDetail(@Path("id") int id);
}
