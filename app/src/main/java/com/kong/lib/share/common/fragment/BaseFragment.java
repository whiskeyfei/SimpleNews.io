package com.kong.lib.share.common.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by whiskeyfei on 15-8-24.
 */

public class BaseFragment extends Fragment {
    protected IBaseEvent mIBaseFramentEvent;
    protected Context mContext;
    protected Bundle mArguments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mArguments = getArguments();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        mIBaseFramentEvent = (IBaseEvent) activity;
//        mContext = activity;
//        mIBaseFramentEvent.onAttachActivity(this);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext = null;
//        if (mIBaseFramentEvent != null) {
//            mIBaseFramentEvent.onDetachActivity(this);
//        }
//        mIBaseFramentEvent = null;
    }
}
