package com.kong;


import android.app.Application;

import com.kong.lib.AppRun;


public class AppApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		AppRun.get().init(getApplicationContext());
//		LifecycleImpl.init(this);
	}
}
