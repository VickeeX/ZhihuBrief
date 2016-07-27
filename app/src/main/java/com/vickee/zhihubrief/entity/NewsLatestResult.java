package com.vickee.zhihubrief.entity;

import java.util.List;

/**
 * Created by Vickee on 2016/7/13.
 */
public class NewsLatestResult {

    /**
     * date : 20160714
     * stories : [{"title":"我的工作是照顾 100 头在草原飞跑的神兽","ga_prefix":"071409","images":["http://pic4.zhimg.com/aa73ece7074af13d55c897c697e31143.jpg"],"multipic":true,"type":0,"id":8553080},{"images":["http://pic2.zhimg.com/9a9c3c008066ee0f724327560eac380d.jpg"],"type":0,"id":8560359,"ga_prefix":"071408","title":"从投行这个高学历人才挤破头想进的地方，离开"},{"images":["http://pic2.zhimg.com/a4ea2636507026b1068a23931409b1d1.jpg"],"type":0,"id":8562726,"ga_prefix":"071407","title":"英国脱欧日元升值，这事儿正说明日元并不保值"},{"images":["http://pic2.zhimg.com/9554aecb66d4c64055857c0c038bd571.jpg"],"type":0,"id":8561358,"ga_prefix":"071407","title":"最近刷屏的「葛优躺」，背后的故事还要说回 23 年前"},{"images":["http://pic2.zhimg.com/ded2566d08246dc9c41e04c5429d6b71.jpg"],"type":0,"id":8562326,"ga_prefix":"071407","title":"作为妇产科男医生，我来说说卫生棉条"},{"images":["http://pic4.zhimg.com/e857c60bfcfdf7336d2c3c59d353331b.jpg"],"type":0,"id":8563170,"ga_prefix":"071407","title":"读读日报 24 小时热门 TOP 5 · 一个贯穿篮球游戏历史的男人"},{"images":["http://pic1.zhimg.com/73a31284b742aec277e01e53f2621170.jpg"],"type":0,"id":8560124,"ga_prefix":"071406","title":"瞎扯 · 如何正确地吐槽"},{"images":["http://pic1.zhimg.com/eb6f366b634cf0f1d1e69152be0f2ecc.jpg"],"type":0,"id":8562046,"ga_prefix":"071406","title":"这里是广告 · 月薪八千闺蜜聚会指南"}]
     * top_stories : [{"image":"http://pic2.zhimg.com/65106d260edd25e638d53b3f16aae879.jpg","type":0,"id":8563170,"ga_prefix":"071407","title":"读读日报 24 小时热门 TOP 5 · 一个贯穿篮球游戏历史的男人"},{"image":"http://pic3.zhimg.com/004defd2dfb8eab34988eacb8e44feaa.jpg","type":0,"id":8553080,"ga_prefix":"071409","title":"我的工作是照顾 100 头在草原飞跑的神兽"},{"image":"http://pic1.zhimg.com/c3e283f1e50215ab821d25c426aba7fc.jpg","type":0,"id":8562326,"ga_prefix":"071407","title":"作为妇产科男医生，我来说说卫生棉条"},{"image":"http://pic4.zhimg.com/3aa8554c9ad14767ee3ca96b2f6d04cb.jpg","type":0,"id":8561358,"ga_prefix":"071407","title":"最近刷屏的「葛优躺」，背后的故事还要说回 23 年前"},{"image":"http://pic2.zhimg.com/9169238f8f8841128e545c12f9300d11.jpg","type":0,"id":8560706,"ga_prefix":"071317","title":"知乎好问题 · 有哪些令人听到就心情大好的歌？"}]
     */

    public String date;
    /**
     * title : 我的工作是照顾 100 头在草原飞跑的神兽
     * ga_prefix : 071409
     * images : ["http://pic4.zhimg.com/aa73ece7074af13d55c897c697e31143.jpg"]
     * multipic : true
     * type : 0
     * id : 8553080
     */

    public List<StoriesBean> stories;
    /**
     * image : http://pic2.zhimg.com/65106d260edd25e638d53b3f16aae879.jpg
     * type : 0
     * id : 8563170
     * ga_prefix : 071407
     * title : 读读日报 24 小时热门 TOP 5 · 一个贯穿篮球游戏历史的男人
     */

    public List<TopStoriesBean> top_stories;

    public static class StoriesBean {
        public String title;
        public String ga_prefix;
        public boolean multipic;
        public int type;
        public int id;
        public List<String> images;
    }

    public static class TopStoriesBean {
        public String image;
        public int type;
        public int id;
        public String ga_prefix;
        public String title;
    }
}
