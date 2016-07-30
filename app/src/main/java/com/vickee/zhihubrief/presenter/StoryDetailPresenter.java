package com.vickee.zhihubrief.presenter;

import com.vickee.zhihubrief.entity.NewsContentResult;
import com.vickee.zhihubrief.model.GetRetrofit;
import com.vickee.zhihubrief.model.IGetRetrofit;
import com.vickee.zhihubrief.view.view.IStoryDetailView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Vickee on 2016/7/27.
 */
public class StoryDetailPresenter {
    private static final String TAG = "StoryDetailPresenter";
    private IGetRetrofit retrofit;
    private IStoryDetailView storyDetailView;

    public StoryDetailPresenter(IStoryDetailView storyDetailView) {
        this.storyDetailView = storyDetailView;
        retrofit = new GetRetrofit();
    }

    public void getStoryDetail() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                retrofit.getService()
                        .getStoryDetail(storyDetailView.getId())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<NewsContentResult>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                storyDetailView.getNewsFailed();
                            }

                            @Override
                            public void onNext(NewsContentResult contentResult) {
                                storyDetailView.loadContentData(contentResult);
                            }
                        });
            }
        }).start();

    }
}
