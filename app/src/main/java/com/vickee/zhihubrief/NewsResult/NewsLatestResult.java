package com.vickee.zhihubrief.NewsResult;

import java.util.List;

/**
 * Created by Vickee on 2016/7/13.
 */
public class NewsLatestResult {
    private String date;
    private Stories[] stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Stories[] getStories() {
        return stories;
    }

    public void setStories(Stories[] stories) {
        this.stories = stories;
    }

    public static class Stories {
        private String title;
        private List<String> images;
        private String ga_prefix;
        private int type;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
