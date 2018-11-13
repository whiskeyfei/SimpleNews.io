package com.kong;


import android.app.Application;

import com.kong.lib.AppRun;
import com.luojilab.component.componentlib.router.Router;


public class AppApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		AppRun.get().init(getApplicationContext());
//		LifecycleImpl.init(this);

		// 主动加载一下组件
//		Router.registerComponent("com.luojilab.reader.applike.ReaderAppLike");
//		Router.registerComponent("com.luojilab.share.applike.ShareApplike");
		Router.registerComponent("com.example.mylibrary.TostApplicationLike");
	}
}
