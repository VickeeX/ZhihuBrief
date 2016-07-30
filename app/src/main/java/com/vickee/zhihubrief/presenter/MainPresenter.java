package com.vickee.zhihubrief.presenter;

import com.vickee.zhihubrief.entity.NewsLatestResult;
import com.vickee.zhihubrief.model.GetRetrofit;
import com.vickee.zhihubrief.model.IGetRetrofit;
import com.vickee.zhihubrief.view.view.IMainView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Vickee on 2016/7/27.
 */
public class MainPresenter {
    private static final String TAG = "MainPresenter";
    private IGetRetrofit getRetrofit;
    private IMainView mainView;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        getRetrofit = new GetRetrofit();
    }

    public void getLatestNews() {
        mainView.showLoading();
        getRetrofit.getService()
                .getLatestNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<NewsLatestResult>() {
                    @Override
                    public void onCompleted() {
                        mainView.hideLoading();

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.getNewsFailed();
                        mainView.hideLoading();
                    }

                    @Override
                    public void onNext(NewsLatestResult result) {
                        mainView.setNewsAdapter(result.stories);
                    }
                });
    }
}
