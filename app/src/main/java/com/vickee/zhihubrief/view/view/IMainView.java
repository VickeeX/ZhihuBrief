package com.vickee.zhihubrief.view.view;

import com.vickee.zhihubrief.entity.NewsLatestResult;

import java.util.List;

/**
 * Created by Vickee on 2016/7/27.
 */
public interface IMainView {
    public void setNewsAdapter(List<NewsLatestResult.StoriesBean> stories);

}
