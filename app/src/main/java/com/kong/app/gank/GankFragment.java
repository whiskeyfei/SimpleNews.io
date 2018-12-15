package com.kong.app.gank;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.baselib.utlis.ResourceUtil;
import com.kong.R;
import com.kong.app.me.ToolBarFragment;

import java.util.List;

/**
 * Created by CaoPengfei on 17/8/2.
 */

public class GankFragment extends ToolBarFragment implements GankContract.View{

    private static final String TAG = "GankFragment";
    private static final String ARG_YEAR = "year";
    private static final String ARG_MONTH = "month";
    private static final String ARG_DAY = "day";

    private int mYear, mMonth, mDay;
    private RecyclerView mRecyclerView;
    private ViewStub mErrorStup;
    private GankAdapter mGankAdapter;
    private TextView mTitle;

    private GangPresenter mGangPresenter;

    public static GankFragment newInstance(int year, int month, int day) {
        GankFragment fragment = new GankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_YEAR, year);
        args.putInt(ARG_MONTH, month);
        args.putInt(ARG_DAY, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gank;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGankAdapter = new GankAdapter();
        mGangPresenter = new GangPresenter(new GankModel(),this);
        parseArguments();
    }

    private void parseArguments() {
        Bundle bundle = getArguments();
        mYear = bundle.getInt(ARG_YEAR);
        mMonth = bundle.getInt(ARG_MONTH);
        mDay = bundle.getInt(ARG_DAY);
    }

    @Override
    public View createView(View view, ViewGroup container, Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.me_recycle_view);
        mErrorStup = (ViewStub) view.findViewById(R.id.stub_error_view);

        mTitle = (TextView) view.findViewById(R.id.base_toolbar_title);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.me_recycle_view);
        mTitle.setText(ResourceUtil.getString(R.string.tab_rec));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mGankAdapter);
        mGangPresenter.loadData(mYear, mMonth, mDay);
        return view;
    }

    @Override
    public void showError() {
        mErrorStup.inflate().setVisibility(View.VISIBLE);
    }

    @Override
    public void showReslut(List<Gank> gankList) {
        mGankAdapter.setLists(gankList);
        mGankAdapter.notifyDataSetChanged();
    }

}
