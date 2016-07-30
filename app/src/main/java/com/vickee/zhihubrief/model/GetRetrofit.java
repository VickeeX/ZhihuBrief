package com.vickee.zhihubrief.model;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vickee on 2016/7/27.
 */
public class GetRetrofit implements IGetRetrofit {
    public static final String BASE_URL = "http://news-at.zhihu.com";
    public Retrofit retrofit;

    public GetRetrofit() {
//        OkHttpClient client = new OkHttpClient();
//        RestAdapter restAdapter = new RestAdapter.Builder()
//                .setClient(new OkClient(client))
//                .setEndpoint(MainFactory.HOST)
//                .setConverter(new GsonConverter(gson))
//                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .build();
//        mService = restAdapter.create(GuDong.class);
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        Log.i("getRetrofit","new Retrofit");
    }

    @Override
    public ZhihuService getService() {
        return retrofit.create(ZhihuService.class);
    }
}
