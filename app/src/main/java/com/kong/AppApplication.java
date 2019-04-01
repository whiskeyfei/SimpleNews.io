package com.kong;


import android.app.Application;

import com.baselib.app.AppRunTime;
import com.luojilab.component.componentlib.router.Router;


public class AppApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		AppRunTime.get().init(getApplicationContext());
		LifecycleImpl.init(this);
		Router.registerComponent("com.kong.web.service.DetailApplicationLike");
	}
}
