package com.kong.home.tab;

import com.kong.R;
import com.kong.app.blog.BlogFragment;
import com.kong.app.gank.GankDate;
import com.kong.app.gank.GankFragment;
import com.kong.app.news.NewsFragment;
import com.kong.lib.fragment.BaseFragment;
import com.kong.lib.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoPengfei on 17/8/5.
 */

public class HomeFactory {

    public final static List<TabItemModel> mTabItems = new ArrayList<TabItemModel>();

    static {
        mTabItems.add(new TabItemModel(R.drawable.icon_home_normal, R.drawable.icon_home_focus, ResourceUtil.getString(R.string.tab_home)));
        mTabItems.add(new TabItemModel(R.drawable.icon_blog_normal, R.drawable.icon_blog_focus, ResourceUtil.getString(R.string.tab_blog)));
        mTabItems.add(new TabItemModel(R.drawable.icon_recommend_normal, R.drawable.icon_recommend_focus, ResourceUtil.getString(R.string.tab_rec)));
//        mTabItems.add(new TabItemModel(R.drawable.icon_me_normal, R.drawable.icon_me_focus, ResourceUtil.getString(R.string.tab_me)));
    }

    public final static List<BaseFragment> mTabFragment = new ArrayList<>();

    static {
        mTabFragment.add(NewsFragment.newInstance());
        mTabFragment.add(BlogFragment.newInstance());
        mTabFragment.add(GankFragment.newInstance(GankDate.get().getYear(), GankDate.get().getMonth() + 1, GankDate.get().getDay()));
//        mTabFragment.add(MeFragment.newInstance());
    }

}
