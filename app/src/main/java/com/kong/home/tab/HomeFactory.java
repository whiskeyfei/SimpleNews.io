package com.kong.home.tab;

import com.baselib.utlis.ResourceUtil;
import com.kong.R;
import com.kong.app.me.DiscoverFragment;
import com.kong.app.recomend.HomeFragment;
import com.kong.app.me.MeFragment;
import com.kong.app.me.SubjectFragment;
import com.kong.lib.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuming on 17/8/5.
 */

public class HomeFactory {

    public final static List<TabItemModel> mTabItems = new ArrayList<TabItemModel>();

    static {
        mTabItems.add(new TabItemModel(R.drawable.icon_home_normal, R.drawable.icon_home_focus, ResourceUtil.getString(R.string.tab_home)));
        mTabItems.add(new TabItemModel(R.drawable.icon_blog_normal, R.drawable.icon_blog_focus, ResourceUtil.getString(R.string.tab_discover)));
        mTabItems.add(new TabItemModel(R.drawable.icon_recommend_normal, R.drawable.icon_recommend_focus, ResourceUtil.getString(R.string.tab_knowledge)));
        mTabItems.add(new TabItemModel(R.drawable.icon_blog_normal, R.drawable.icon_blog_focus, ResourceUtil.getString(R.string.tab_me)));
    }

    public final static List<BaseFragment> mTabFragment = new ArrayList<>();

    static {
        mTabFragment.add(HomeFragment.newInstance());
        mTabFragment.add(DiscoverFragment.newInstance());
        mTabFragment.add(SubjectFragment.newInstance());
        mTabFragment.add(MeFragment.newInstance());
    }

}
