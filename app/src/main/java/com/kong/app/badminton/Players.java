package com.kong.app.badminton;

import java.util.List;

/**
 * Created by CaoPengfei on 17/7/3.
 */

public class Players {

    private int current_page;
    private int from;
    private int last_page;
    private String next_page_url;
    private String path;
    private int per_page;
    private Object prev_page_url;
    private int to;
    private int total;
    private List<PlayerBean> data;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getLast_page() {
        return last_page;
    }

    public void setLast_page(int last_page) {
        this.last_page = last_page;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public Object getPrev_page_url() {
        return prev_page_url;
    }

    public void setPrev_page_url(Object prev_page_url) {
        this.prev_page_url = prev_page_url;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PlayerBean> getData() {
        return data;
    }

    public void setData(List<PlayerBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Players{" +
                "current_page=" + current_page +
                ", from=" + from +
                ", last_page=" + last_page +
                ", next_page_url='" + next_page_url + '\'' +
                ", path='" + path + '\'' +
                ", per_page=" + per_page +
                ", prev_page_url=" + prev_page_url +
                ", to=" + to +
                ", total=" + total +
                ", data=" + data +
                '}';
    }

    public static class PlayerBean {

        private int id;
        private String name;
        private String img;
        private String country;
        private String englist_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getEnglist_name() {
            return englist_name;
        }

        public void setEnglist_name(String englist_name) {
            this.englist_name = englist_name;
        }

        @Override
        public String toString() {
            return "PlayerBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", img='" + img + '\'' +
                    ", country='" + country + '\'' +
                    ", englist_name='" + englist_name + '\'' +
                    '}';
        }
    }
}
