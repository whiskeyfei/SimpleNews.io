package com.lauren.simplenews.weather.adapter;

import android.content.Context;

import com.fei.library.adapter.DPQuickAdapter;
import com.fei.library.adapter.DPViewHolder;
import com.lauren.simplenews.R;
import com.lauren.simplenews.weather.model.WeatherBean;

import java.util.List;

/**
 * Created by whiskeyfei on 16-1-9.
 */
public class WeatherAdapter extends DPQuickAdapter<WeatherBean> {

    public WeatherAdapter(Context context, List list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    public void convert(DPViewHolder holder, WeatherBean model) {
        holder.setText(R.id.date, model.getWeek());
        holder.setImageResource(R.id.weatherImage, model.getImageRes());
        holder.setText(R.id.weather, model.getTemperature() + model.getWind());
    }
}
