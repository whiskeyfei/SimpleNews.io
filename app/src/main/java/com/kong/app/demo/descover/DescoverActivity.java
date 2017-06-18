package com.kong.app.demo.descover;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kong.R;
import com.kong.app.news.base.ThemeActivity;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by CaoPengfei on 17/6/18.
 */

public class DescoverActivity extends ThemeActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;
    List<Object> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descover);
        initBar();
        init();
    }

    private void initBar() {
        mToolbar = (Toolbar) findViewById(R.id.descover_toolbar);
        initToolBar(mToolbar, R.string.descover);
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.descover_recycle_view);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (list.get(position) instanceof Header) ? 4 : 1;
            }
        });
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(GridItem.class, new GridItemViewBinder());
        mAdapter.register(Header.class, new HeadertemViewBinder());
        list.add(new Header("这是一个Header item :0"));
        for (int i = 0; i < 100; i++) {
            GridItem item = new GridItem();
            item.title = "item:" + (i+1);
            list.add(item);
        }
        mAdapter.setItems(list);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);

    }
}
