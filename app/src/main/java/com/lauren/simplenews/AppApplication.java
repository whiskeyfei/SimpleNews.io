package com.lauren.simplenews;


import android.app.Application;

import com.library.AppRun;


public class AppApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		AppRun.get().init(getApplicationContext());
	}
}
