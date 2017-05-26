package com.kong.lib.share.common.mvp;

public class Injection {

    public static ISchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
