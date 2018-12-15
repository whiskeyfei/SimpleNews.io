package com.kong.web.service;

import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.componentservice.toast.detail.IDetailService;

public class DetailApplicationLike implements IApplicationLike {

    @Override
    public void onCreate() {
        Router.getInstance().addService(IDetailService.class.getSimpleName(), new DetailService());
    }

    @Override
    public void onStop() {
        Router.getInstance().removeService(IDetailService.class.getSimpleName());
    }
}
