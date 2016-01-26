package com.fei.library.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fei.library.inter.IBaseFragmentEvent;

/**
 * Created by whiskeyfei on 15-8-24.
 */

public class DPBaseFragment extends BaseFragment {
    protected IBaseFragmentEvent mIBaseFramentEvent;
    protected Context mContext;
    protected Bundle mArguments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mArguments = getArguments();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mIBaseFramentEvent = (IBaseFragmentEvent) activity;
        mContext = activity;
        mIBaseFramentEvent.onAttachActivity(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext = null;
        if (mIBaseFramentEvent != null) {
            mIBaseFramentEvent.onDetachActivity(this);
        }
        mIBaseFramentEvent = null;
    }
}
