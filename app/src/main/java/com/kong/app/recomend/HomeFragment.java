package com.kong.app.recomend;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baselib.utlis.ResourceUtil;
import com.baselib.widget.TitleBarLayout;
import com.kong.R;
import com.kong.app.NSViewModel;
import com.kong.app.demo.about.TextItemViewBinder;
import com.kong.app.demo.about.TextViewItem;
import com.kong.app.demo.me.AvatarItem;
import com.kong.app.demo.me.AvatarItemViewBinder;
import com.kong.app.demo.me.SettingImgTvItem;
import com.kong.app.demo.me.SettingImgTvItemViewBinder;
import com.kong.app.me.MeModel;
import com.kong.app.search.SearchEntry;
import com.kong.lib.fragment.BaseFragment;
import com.kong.lib.utils.ImageLoaderUtils;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by wuming on 17/7/27.
 */

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";

    private NSViewModel mNSViewModel;
    private TitleBarLayout mTitleBarLayout;
    private XBanner mXBanner;
    private RecyclerView mRecyclerView;

    private MultiTypeAdapter mAdapter;
    private List<Object> list = new ArrayList<>();

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, null);
        mTitleBarLayout = view.findViewById(R.id.me_titlebar_layout);
        mTitleBarLayout.showSearchView(ResourceUtil.getString(R.string.tab_rec), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchEntry.startSearchActivity(getActivity());
            }
        });
        mNSViewModel = ViewModelProviders.of(getActivity()).get(NSViewModel.class);
        mXBanner = view.findViewById(R.id.recommend_banner);
        mXBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageLoaderUtils.display(ResourceUtil.getContext(),(ImageView) view,((Banner)model).imagePath,R.drawable.circle_point,R.drawable.circle_point);
            }
        });
        mRecyclerView = view.findViewById(R.id.me_recycle_view);
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNSViewModel.getBannerList().observe(this, new Observer<List<Banner>>() {
            @Override
            public void onChanged(@Nullable List<Banner> bannerModels) {
                Log.i(TAG, "onChanged bannerModels: " + bannerModels);
                mXBanner.setBannerData(bannerModels);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mNSViewModel.start();
    }

    private void init() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(SettingImgTvItem.class, new SettingImgTvItemViewBinder());
        mAdapter.register(AvatarItem.class, new AvatarItemViewBinder());
        mAdapter.register(TextViewItem.class, new TextItemViewBinder());
        list.clear();
        list.add(new AvatarItem());

        int size = ME_MODELS.size();
        for (int i = 0; i < size; i++) {
            SettingImgTvItem item = new SettingImgTvItem();
            item.title = ME_MODELS.get(i).name;
            item.icon = ME_MODELS.get(i).icon;
            list.add(item);
        }

        TextViewItem about = new TextViewItem();
        about.text = ResourceUtil.getString(R.string.about_copyright);
        list.add(about);

        mAdapter.setItems(list);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }

    private final static List<MeModel> ME_MODELS = new ArrayList<>();

    public static final int TYPE_ABOUT = 1;
    public static final int TYPE_PROBLEM = 2;
    public static final int TYPE_SETTING = 3;

    static {
        ME_MODELS.add(new MeModel(TYPE_ABOUT, R.drawable.ic_about, "关于我们"));
        ME_MODELS.add(new MeModel(TYPE_PROBLEM, R.drawable.ic_about, "常见问题"));
        ME_MODELS.add(new MeModel(TYPE_SETTING, R.drawable.ic_settings, "设置"));
    }

}
