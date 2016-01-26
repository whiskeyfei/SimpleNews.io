package com.lauren.simplenews.weather.view;

import com.whiskeyfei.mvp.base.IMVPBaseView;
import com.lauren.simplenews.weather.model.WeatherBean;

import java.util.List;

/**
 * Description :
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 2015/12/22
 */
public interface IWeatherView extends IMVPBaseView {

    void showProgress();

    void hideProgress();

    void showWeatherLayout();

    void setWeatherData(List<WeatherBean> lists);

    void showErrorToast(String msg);

    void showWeatherResult(WeatherBean bean);


}
