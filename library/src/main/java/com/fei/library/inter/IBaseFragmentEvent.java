package com.fei.library.inter;

import android.os.Bundle;

import com.fei.library.fragment.DPBaseFragment;

/**
 * Created by whiskeyfei on 15-11-25.
 */
public interface IBaseFragmentEvent {
    void onSwitchFragment(DPBaseFragment fragment, Bundle bundle);
    void onAttachActivity(DPBaseFragment fragment);
    void onDetachActivity(DPBaseFragment fragment);
}
