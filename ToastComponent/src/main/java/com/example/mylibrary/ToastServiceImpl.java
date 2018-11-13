package com.example.mylibrary;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.luojilab.componentservice.toast.ToastService;

/**
 * Created by whiskeyfei on 2018/11/12.
 */

public class ToastServiceImpl implements ToastService {

    @Override
    public void showToast(String title) {
        Log.e("cpf", "showToast,title:" + title);
    }

    @Override
    public void openMain(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ToastMainActivity.class);
        context.startActivity(intent);
    }
}
