package com.kong.app.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kong.R;
import com.kong.app.news.NewsEntry;
import com.kong.app.news.adapter.DemoAdapter;
import com.kong.app.news.adapter.OnItemClickListener;
import com.kong.app.news.beans.NoteModel;
import com.library.AppRun;
import com.library.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoPengfei on 17/6/9.
 */

public class DemoActivity extends BaseActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private static final int [] sId = {R.raw.demo};
    private static final String [] sNostLists = {"TabLayoutDemo"};
    private List<NoteModel> mNoteModels;
    private DemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initBar();
        initList();
    }

    private void initBar() {
        mToolbar = (Toolbar) findViewById(R.id.demo_toolbar);
        initToolBar(mToolbar);
    }

    private void initList() {
        mRecyclerView = (RecyclerView) findViewById(R.id.note_recycle_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(AppRun.get().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mNoteModels = new ArrayList<NoteModel>();
        mNoteModels.clear();

        for (int i = 0; i < sNostLists.length; i++) {
            NoteModel model = new NoteModel();
            model.id = sId[i];
            model.name = sNostLists[i];
            mNoteModels.add(model);
        }
        mAdapter = new DemoAdapter(AppRun.get().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mAdapter.setDate(mNoteModels);
        mRecyclerView.setAdapter(mAdapter);
    }

    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(DemoActivity.this,NoteActivity.class);
            intent.putExtra("key",mNoteModels.get(position));
            NewsEntry.get().startNote(DemoActivity.this,intent);
        }
    };
}
