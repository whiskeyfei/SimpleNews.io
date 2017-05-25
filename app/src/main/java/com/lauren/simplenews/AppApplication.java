package com.lauren.simplenews;


import android.app.Application;
import android.content.Context;

import com.library.AppRun;
import com.orhanobut.logger.Logger;


public class AppApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		AppRun.get().init(getApplicationContext());
		Logger.init("simplenews.io");
	}
}
