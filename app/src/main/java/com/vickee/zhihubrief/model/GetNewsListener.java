package com.vickee.zhihubrief.model;

import com.vickee.zhihubrief.entity.NewsLatestResult;

/**
 * Created by Vickee on 2016/7/27.
 */
public interface GetNewsListener {
    public void getNewsSuccess(NewsLatestResult result);
    public void getNewsFailed();
}
