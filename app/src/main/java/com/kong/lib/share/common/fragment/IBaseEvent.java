package com.kong.lib.share.common.fragment;

import android.os.Bundle;

/**
 * Created by whiskeyfei on 15-11-25.
 */
public interface IBaseEvent {
    void onSwitchFragment(BaseFragment fragment, Bundle bundle);
    void onAttachActivity(BaseFragment fragment);
    void onDetachActivity(BaseFragment fragment);
}
