package com.kong.app.demo.about;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kong.R;
import com.kong.app.news.base.ThemeActivity;
import com.library.utils.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by CaoPengfei on 17/5/26.
 * <p>
 * https://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
 */

public class AboutActivity3 extends ThemeActivity {

    private static int[][] title = {
            {R.string.about_version, R.string.about_version},
            {R.string.about_des, R.string.description},
            {R.string.about_github, R.string.github_url},
            {R.string.about_name, R.string.author},
            {R.string.about_blog, R.string.blog},
            {R.string.about_email, R.string.email}
    };

    private List<Object> mObjectList = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        toolbar.setTitle(ResourceUtil.getString(R.string.about));
        initToolBar(toolbar);
        initContent();
    }

    private void initContent() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.about3_recycle_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        MultiTypeAdapter adapter = new MultiTypeAdapter();
        adapter.register(ContentModel.class, new About3ItemViewBinder());
        adapter.register(TextViewItem.class, new TextItemViewBinder());
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < title.length; i++) {
            ContentModel model = new ContentModel();
            model.title = ResourceUtil.getString(title[i][0]);
            model.desc = ResourceUtil.getString(title[i][1]);
            mObjectList.add(model);
        }

        TextViewItem item = new TextViewItem();
        item.text = ResourceUtil.getString(R.string.about_copyright);
        mObjectList.add(item);

        adapter.setItems(mObjectList);
        adapter.notifyDataSetChanged();
    }
}
