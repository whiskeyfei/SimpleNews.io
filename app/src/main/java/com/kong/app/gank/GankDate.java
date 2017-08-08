package com.kong.app.gank;

import java.util.Calendar;

/**
 * Created by CaoPengfei on 17/8/2.
 */

public class GankDate {

    private Calendar calendar;
    private static GankDate mInstance = new GankDate();

    public GankDate() {
        calendar = Calendar.getInstance();
    }

    public static GankDate get() {
        if (mInstance == null) {
            mInstance = new GankDate();
        }
        return mInstance;
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public int getDay() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (Calendar.SATURDAY == weekDay){
            day--;
        }else if(Calendar.SUNDAY == weekDay){
            day = day -2;
        }
        return day;
    }
}
