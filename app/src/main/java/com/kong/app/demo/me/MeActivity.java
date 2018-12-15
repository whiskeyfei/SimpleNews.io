package com.kong.app.demo.me;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baselib.utlis.ResourceUtil;
import com.kong.R;
import com.kong.app.demo.about.TextItemViewBinder;
import com.kong.app.demo.about.TextViewItem;
import com.kong.app.news.base.ToolBarActivity;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by CaoPengfei on 17/6/18.
 */

public class MeActivity extends ToolBarActivity {

    private RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;
    private List<Object> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.descover);
        init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_me;
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.me_recycle_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(SettingImgTvItem.class, new SettingImgTvItemViewBinder());
        mAdapter.register(AvatarItem.class,new AvatarItemViewBinder());
        mAdapter.register(TextViewItem.class, new TextItemViewBinder());

        list.add(new AvatarItem());
        for (int i = 0; i < 10; i++) {
            SettingImgTvItem item = new SettingImgTvItem();
            item.title = "item:" + (i+1);
            list.add(item);
        }

        TextViewItem item1 = new TextViewItem();
        item1.text = ResourceUtil.getString(R.string.about_copyright);
        list.add(item1);

        mAdapter.setItems(list);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
    }
}
