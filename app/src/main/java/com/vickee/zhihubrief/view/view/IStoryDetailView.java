package com.vickee.zhihubrief.view.view;

import com.vickee.zhihubrief.entity.NewsContentResult;

/**
 * Created by Vickee on 2016/7/27.
 */
public interface IStoryDetailView {
    void loadContentData(NewsContentResult contentResult);
    void getNewsFailed();
    int getId();
}
