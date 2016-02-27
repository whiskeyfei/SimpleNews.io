package com.lauren.simplenews.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lauren.simplenews.R;
import com.lauren.simplenews.utils.ImageLoaderUtils;

/**
 * Created by whiskeyfei on 16-2-27.
 */
public class NewCardView extends android.support.v7.widget.CardView {
    private ImageView mImageView;
    private TextView mTitle, mDesc;
    private Context mContext;

    public NewCardView(Context context) {
        super(context);
        init(context);
    }

    public NewCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NewCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layout.inflate(R.layout.item_news, null);
        LinearLayout mLayout = (LinearLayout) view.findViewById(R.id.main);
        mImageView = (ImageView) mLayout.findViewById(R.id.ivNews);
        mTitle = (TextView) mLayout.findViewById(R.id.tvTitle);
        mDesc = (TextView) mLayout.findViewById(R.id.tvDesc);
        addView(view);
    }

    public void setTitle(String string) {
        if (mTitle != null) {
            mTitle.setText(string);
        }
    }

    public void setContent(String string) {
        if (mDesc != null) {
            mDesc.setText(string);
        }
    }

    public void setImageURL(String imageUrl) {
        if (mImageView != null){
            ImageLoaderUtils.display(mContext,mImageView,imageUrl);
        }
    }
}
