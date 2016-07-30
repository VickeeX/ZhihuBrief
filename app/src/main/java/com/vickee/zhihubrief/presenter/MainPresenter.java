package com.vickee.zhihubrief.presenter;

import com.vickee.zhihubrief.entity.NewsLatestResult;
import com.vickee.zhihubrief.model.GetRetrofit;
import com.vickee.zhihubrief.model.IGetRetrofit;
import com.vickee.zhihubrief.view.view.IMainView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                getRetrofit.getService()
                        .getLatestNews()
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new Func1<NewsLatestResult, List<NewsLatestResult.StoriesBean>>() {
                            @Override
                            public List<NewsLatestResult.StoriesBean> call(NewsLatestResult result) {
                                return result.stories;
                            }
                        })
                        .subscribe(new Subscriber<List<NewsLatestResult.StoriesBean>>() {
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
                            public void onNext(List<NewsLatestResult.StoriesBean> storiesBeen) {
                                mainView.setNewsAdapter(storiesBeen);
                            }
                        });
            }
        }).start();


//        Log.i(TAG,result.stories.size()+":"+result.stories.get(0).title);

    }
}
