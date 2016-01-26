package com.lauren.simplenews.news.model;

import rx.Observable;

public interface INewsModel {
    Observable getNewList(String url, int type);

    Observable getDetailNew(String id);
}
