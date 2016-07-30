package com.vickee.zhihubrief.presenter;

import com.vickee.zhihubrief.entity.NewsContentResult;
import com.vickee.zhihubrief.model.GetRetrofit;
import com.vickee.zhihubrief.model.IGetRetrofit;
import com.vickee.zhihubrief.view.view.IStoryDetailView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        retrofit.getService()
                .getStoryDetail(storyDetailView.getId())
                .subscribeOn(Schedulers.io())
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
}
