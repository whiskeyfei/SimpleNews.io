package com.lauren.simplenews.event;

import rx.Observable;

public interface INewsModel {

    Observable getNewlist(String url);

}
