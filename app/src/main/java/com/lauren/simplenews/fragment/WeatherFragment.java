//package com.lauren.simplenews.fragment;
//
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.fei.library.fragment.DPBaseFragment;
//import com.lauren.simplenews.R;
//import com.lauren.simplenews.adapter.WeatherAdapter;
//import com.lauren.simplenews.beans.WeatherBean;
//import com.lauren.simplenews.presenter.WeatherPresenter;
//import com.lauren.simplenews.view.IWeatherView;
//
//import java.util.List;
//
///**
// * Description :
// * Author : lauren
// * Email  : lauren.liuling@gmail.com
// * Blog   : http://www.liuling123.com
// * Date   : 15/12/21
// */
//public class WeatherFragment extends DPBaseFragment implements IWeatherView {
//
//    private WeatherPresenter mWeatherPresenter;
//    private TextView mTodayTV;
//    private ImageView mTodayWeatherImage;
//    private TextView mTodayTemperatureTV;
//    private TextView mTodayWindTV;
//    private TextView mTodayWeatherTV;
//    private TextView mCityTV;
//    private ProgressBar mProgressBar;
//    private LinearLayout mWeatherLayout;
//    private WeatherAdapter mWeatherAdapter;
//    private ListView mListView;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mWeatherPresenter = new WeatherPresenter(this);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_weather, null);
//        mTodayTV = (TextView) view.findViewById(R.id.today);
//        mTodayWeatherImage = (ImageView) view.findViewById(R.id.weatherImage);
//        mTodayTemperatureTV = (TextView) view.findViewById(R.id.weatherTemp);
//        mTodayWindTV = (TextView) view.findViewById(R.id.wind);
//        mTodayWeatherTV = (TextView) view.findViewById(R.id.weather);
//        mCityTV = (TextView)view.findViewById(R.id.city);
//        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
//        mWeatherLayout = (LinearLayout) view.findViewById(R.id.weather_layout);
//        mListView = (ListView) view.findViewById(R.id.listview);
//        mWeatherPresenter.loadWeatherData();
//        return view;
//    }
//
//    @Override
//    public void showProgress() {
//        mProgressBar.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void hideProgress() {
//        mProgressBar.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void showWeatherLayout() {
//        mWeatherLayout.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
//
//    @Override
//    public void setWeatherData(List<WeatherBean> lists) {
//        mWeatherAdapter = new WeatherAdapter(getActivity(), lists, R.layout.item_weather);
//        mListView.setAdapter(mWeatherAdapter);
//    }
//
//    @Override
//    public void showErrorToast(String msg) {
//        Snackbar.make(getActivity().findViewById(R.id.drawer_layout), msg, Snackbar.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void showWeatherResult(WeatherBean bean) {
//        mCityTV.setText("北京");
//        mTodayTV.setText(bean.getDate());
//        mTodayTemperatureTV.setText(bean.getTemperature());
//        mTodayWeatherTV.setText(bean.getWeather());
//        mTodayWindTV.setText(bean.getWind());
//        mTodayWeatherImage.setImageResource(bean.getImageRes());
//    }
//}
