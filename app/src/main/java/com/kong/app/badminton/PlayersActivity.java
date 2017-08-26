package com.kong.app.badminton;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kong.R;
import com.kong.app.blog.tool.OnGridViewScollListener;
import com.kong.app.demo.descover.GridItemViewBinder;
import com.kong.app.demo.descover.Header;
import com.kong.app.demo.descover.HeadertemViewBinder;
import com.kong.app.news.base.ToolBarActivity;
import com.kong.lib.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by CaoPengfei on 17/6/18.
 */

public class PlayersActivity extends ToolBarActivity implements PlayerContract.View, OnGridViewScollListener.OnLoadListener{

    private static final String TAG = "PlayersActivity";
    private RecyclerView mRecyclerView;
    private MultiTypeAdapter mAdapter;
    private List<Object> mObjectList = new ArrayList<Object>();
    private PlayerContract.Presenter mPlayerPresenter;
    private int mPageIndex = 1;
    private int LastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("球星");
        init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_descover;
    }

    private void init() {
        new PlayerPresenter(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.descover_recycle_view);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (mObjectList.get(position) instanceof Header) ? 4 : 1;
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new OnGridViewScollListener(layoutManager,this));
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(Players.PlayerBean.class, new GridItemViewBinder());
        mAdapter.register(Header.class, new HeadertemViewBinder());
        mRecyclerView.setAdapter(mAdapter);
        mObjectList.add(new Header("这是一个Header item :0"));
        mPlayerPresenter.loadMore(mPageIndex);
    }

    @Override
    public void setPresenter(PlayerContract.Presenter presenter) {
        mPlayerPresenter = presenter;
    }

    @Override
    public void onSuccess(Players players) {
        LastPage = players.getLast_page();
        int size = ListUtils.getCount(players.getData());
        for (int i = 0; i < size; i++) {
            Players.PlayerBean bean = players.getData().get(i);
            mObjectList.add(bean);
        }
        mAdapter.setItems(mObjectList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadMore() {
        Log.i(TAG, "loadMore mPageIndex:"+mPageIndex);
        if (mPageIndex > LastPage ){
            Log.i(TAG, "loadMore page");
            return;
        }
        mPageIndex+=1;
        mPlayerPresenter.loadMore(mPageIndex);
    }
}
