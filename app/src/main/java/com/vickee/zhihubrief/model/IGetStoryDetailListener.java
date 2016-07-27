package com.vickee.zhihubrief.model;

import com.vickee.zhihubrief.entity.NewsContentResult;

/**
 * Created by Vickee on 2016/7/27.
 */
public interface IGetStoryDetailListener {
    void getNewsSuccess(NewsContentResult contentResult);
    void getNewsFailed();
}
