package com.lauren.simplenews.app;


import android.app.Application;

import com.orhanobut.logger.Logger;


public class AppApplication extends Application {


	private static AppApplication mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		Logger.init("com.lauren.simplenews.app");
	}
	
	public static synchronized AppApplication get(){
		return mInstance;
	}
}
