package com.lauren.simplenews.model;

import rx.Observable;

public interface INewsModel {

    Observable getNewlist(String url);

}
