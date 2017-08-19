package com.kong.lib.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by whiskeyfei on 15-8-24.
 */

public class BaseFragment extends Fragment {
    protected Bundle mArguments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mArguments = getArguments();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
