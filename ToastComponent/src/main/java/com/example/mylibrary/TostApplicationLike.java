package com.example.mylibrary;

import com.luojilab.component.componentlib.applicationlike.IApplicationLike;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.componentservice.toast.ToastService;

/**
 * Created by whiskeyfei on 2018/11/12.
 */

public class TostApplicationLike implements IApplicationLike {

    @Override
    public void onCreate() {
        Router.getInstance().addService(ToastService.class.getSimpleName(), new ToastServiceImpl());
    }

    @Override
    public void onStop() {
        Router.getInstance().removeService(ToastService.class.getSimpleName());
    }
}
