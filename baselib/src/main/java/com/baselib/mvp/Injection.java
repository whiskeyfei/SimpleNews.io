package com.baselib.mvp;

public class Injection {

    public static ISchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
