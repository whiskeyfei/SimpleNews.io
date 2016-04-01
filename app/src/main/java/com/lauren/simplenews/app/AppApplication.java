package com.lauren.simplenews.app;


import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;


public class AppApplication extends Application {


	private static Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		Logger.init("simplenews.io");
	}



	public static Context get(){
		return mContext;
	}
}
