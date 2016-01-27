package com.lauren.simplenews.model;

import rx.Observable;

public interface INewsModel {
    Observable getNewList(String url, int type);

    Observable getDetailNew(String id);
}
