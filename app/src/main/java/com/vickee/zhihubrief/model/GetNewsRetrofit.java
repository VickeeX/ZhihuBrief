package com.vickee.zhihubrief.model;

import com.vickee.zhihubrief.entity.NewsLatestResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vickee on 2016/7/27.
 */
public class GetNewsRetrofit implements IGetNewsRetrofit {
    public static final String BASE_URL = "http://news-at.zhihu.com";

    @Override
    public void getLatestNews(final GetNewsListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        ZhihuService service = retrofit.create(ZhihuService.class);
        Call<NewsLatestResult> call = service.getLatestNews();
        call.enqueue(new Callback<NewsLatestResult>() {
            @Override
            public void onResponse(Call<NewsLatestResult> call, Response<NewsLatestResult> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.getNewsSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsLatestResult> call, Throwable t) {
                    listener.getNewsFailed();
            }
        });
    }
}
