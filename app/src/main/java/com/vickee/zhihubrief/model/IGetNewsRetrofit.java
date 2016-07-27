package com.vickee.zhihubrief.model;

/**
 * Created by Vickee on 2016/7/27.
 */
public interface IGetNewsRetrofit {
    void getLatestNews(IGetNewsListener listener);
    void getStoryDetail(int id,IGetStoryDetailListener listener);
}
