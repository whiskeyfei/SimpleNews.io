package com.lauren.simplenews.mvp;

public class Injection {

    public static ISchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
