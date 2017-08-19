package com.kong.lib.mvp;

public class Injection {

    public static ISchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

}
