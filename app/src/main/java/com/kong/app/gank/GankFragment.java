package com.kong.app.gank;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.kong.R;
import com.kong.app.me.ToolBarFragment;
import com.library.utils.ListUtils;
import com.library.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by CaoPengfei on 17/8/2.
 */

public class GankFragment extends ToolBarFragment {

    private final String TAG = "GankFragment";
    private static final String ARG_YEAR = "year";
    private static final String ARG_MONTH = "month";
    private static final String ARG_DAY = "day";

    int mYear, mMonth, mDay;
    private List<Gank> mGankList;
    private RecyclerView mRecyclerView;
    private ViewStub mErrorStup;
    private GankListAdapter mAdapter;

    private TextView mTitle;

    final IGankApi mIGankApi = new GankApi();

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
        mGankList = new ArrayList<>();
        mAdapter = new GankListAdapter(mGankList);
        parseArguments();
        setRetainInstance(true);
        setHasOptionsMenu(true);
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
        mRecyclerView.setAdapter(mAdapter);
        loadData();
        time = 0;
        return view;
    }

    private int time = 0;

    private void loadData() {
        mIGankApi.getGankResult(mYear, mMonth, mDay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        reLoad();
//                        showError();
                    }

                    @Override
                    public void onNext(GankResult gankResult) {
                        Log.i(TAG, "onNext: " + gankResult);
                        getGankList(gankResult);

                        if (ListUtils.isEmpty(mGankList)){
                            reLoad();
//                            showError();
                        }else{
                            time = 100;
                            mAdapter.setGankList(mGankList);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void reLoad(){
        time++;
        mDay--;
        if (time < 2){
            loadData();
        }else{
            showError();
        }
    }

    private void showError() {
        mErrorStup.inflate().setVisibility(View.VISIBLE);
    }

    private List<Gank> getGankList(GankResult gankResult){
        if (gankResult.results.androidList != null){
            mGankList.addAll(gankResult.results.androidList);
        }
        if (gankResult.results.appList != null){
            mGankList.addAll(gankResult.results.appList);
        }
        if (gankResult.results.iOSList != null){
            mGankList.addAll(gankResult.results.iOSList);
        }
        if (gankResult.results.瞎推荐List != null){
            mGankList.addAll(gankResult.results.瞎推荐List);
        }
        if (gankResult.results.web != null){
            mGankList.addAll(gankResult.results.web);
        }
        if (gankResult.results.拓展资源List != null){
            mGankList.addAll(gankResult.results.拓展资源List);
        }

        Log.i(TAG, "getGankList: " +mGankList);
        return mGankList;
    }

}
