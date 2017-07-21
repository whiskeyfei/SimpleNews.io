package com.kong.app.blog.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by CaoPengfei on 17/6/21.
 */

public class Feed {

    private String title;
    private String description;
    private String home_page_url;
    private String feed_url;
    private boolean expired;
    private List<PostsBean> posts;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHome_page_url() {
        return home_page_url;
    }

    public void setHome_page_url(String home_page_url) {
        this.home_page_url = home_page_url;
    }

    public String getFeed_url() {
        return feed_url;
    }

    public void setFeed_url(String feed_url) {
        this.feed_url = feed_url;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public List<PostsBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsBean> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", home_page_url='" + home_page_url + '\'' +
                ", feed_url='" + feed_url + '\'' +
                ", expired=" + expired +
                ", posts=" + posts +
                '}';
    }

    public static class PostsBean implements Serializable {

        @Override
        public String toString() {
            return "PostsBean{" +
                    "category='" + category + '\'' +
                    ", items=" + items +
                    '}';
        }

        private String category;
        private List<ItemsBean> items;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean implements Serializable {

            private String id;
            private String keywords;
            private String url;
            private String date_published;
            private String date_modified;
            private String title;
            private String author;
            private String description;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            private String cover;

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDate_published() {
                return date_published;
            }

            public void setDate_published(String date_published) {
                this.date_published = date_published;
            }

            public String getDate_modified() {
                return date_modified;
            }

            public void setDate_modified(String date_modified) {
                this.date_modified = date_modified;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            @Override
            public String toString() {
                return "ItemsBean{" +
                        "id='" + id + '\'' +
                        ", keywords='" + keywords + '\'' +
                        ", url='" + url + '\'' +
                        ", date_published='" + date_published + '\'' +
                        ", date_modified='" + date_modified + '\'' +
                        ", title='" + title + '\'' +
                        ", author='" + author + '\'' +
                        ", description='" + description + '\'' +
                        '}';
            }
        }
    }
}
