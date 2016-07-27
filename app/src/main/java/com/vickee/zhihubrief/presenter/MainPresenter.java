package com.vickee.zhihubrief.presenter;

import android.os.Handler;

import com.vickee.zhihubrief.entity.NewsLatestResult;
import com.vickee.zhihubrief.model.IGetNewsListener;
import com.vickee.zhihubrief.model.GetNewsRetrofit;
import com.vickee.zhihubrief.model.IGetNewsRetrofit;
import com.vickee.zhihubrief.view.view.IMainView;

/**
 * Created by Vickee on 2016/7/27.
 */
public class MainPresenter {
    private IGetNewsRetrofit getNewsRetrofit;
    private IMainView mainView;
    private Handler handler = new Handler();

    public MainPresenter(IMainView mainView){
        this.mainView = mainView;
        getNewsRetrofit = new GetNewsRetrofit();
    }

    public void getLatestNews(){
        mainView.showLoading();
        getNewsRetrofit.getLatestNews(new IGetNewsListener() {
            @Override
            public void getNewsSuccess(final NewsLatestResult result) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mainView.setNewsAdapter(result.stories);
                        mainView.hideLoading();
                    }
                });
            }

            @Override
            public void getNewsFailed() {
                mainView.getNewsFailed();
                mainView.hideLoading();
            }
        });
    }
}
