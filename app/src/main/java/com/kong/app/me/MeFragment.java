package com.kong.app.me;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.demo.about.TextItemViewBinder;
import com.kong.app.demo.about.TextViewItem;
import com.kong.app.demo.me.AvatarItem;
import com.kong.app.demo.me.AvatarItemViewBinder;
import com.kong.app.demo.me.SettingImgTvItem;
import com.kong.app.demo.me.SettingImgTvItemViewBinder;
import com.kong.lib.fragment.BaseFragment;
import com.library.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by CaoPengfei on 17/7/27.
 */

public class MeFragment extends BaseFragment {
    private Toolbar mToolbar;
    private TextView mTitle;
    private RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;
    private List<Object> list = new ArrayList<>();

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_me, null);
        mToolbar = (Toolbar) view.findViewById(R.id.base_toolbar);
        mTitle = (TextView) view.findViewById(R.id.base_toolbar_title);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.me_recycle_view);
        init();
        mToolbar.setTitle("");
        mTitle.setText("我的");
        return view;
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
