package com.vickee.zhihubrief.presenter;

import android.os.Handler;

import com.vickee.zhihubrief.entity.NewsContentResult;
import com.vickee.zhihubrief.model.GetNewsRetrofit;
import com.vickee.zhihubrief.model.IGetNewsRetrofit;
import com.vickee.zhihubrief.model.IGetStoryDetailListener;
import com.vickee.zhihubrief.view.view.IStoryDetailView;

/**
 * Created by Vickee on 2016/7/27.
 */
public class StoryDetailPresenter {
    private IGetNewsRetrofit retrofit;
    private IStoryDetailView storyDetailView;
    private Handler handler = new Handler();

    public StoryDetailPresenter(IStoryDetailView storyDetailView) {
        this.storyDetailView = storyDetailView;
        retrofit = new GetNewsRetrofit();
    }

    public void getStoryDetail() {
        retrofit.getStoryDetail(storyDetailView.getId(), new IGetStoryDetailListener() {
            @Override
            public void getNewsSuccess(final NewsContentResult contentResult) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        storyDetailView.loadContentData(contentResult);
                    }
                });
            }

            @Override
            public void getNewsFailed() {
                storyDetailView.getNewsFailed();
            }
        });
    }
}
