package com.kong.app.news.utils;

import com.kong.R;
import com.kong.lib.utils.ResourceUtil;
import com.kong.lib.utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by CaoPengfei on 17/6/28.
 */

public class TimeUtils {

    private static final SimpleDateFormat sSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static java.util.Date sNowTime = new Date();
    private static long nd = 1000 * 24 * 60 * 60;
    private static long nh = 1000 * 60 * 60;
    private static String day = ResourceUtil.getString(R.string.today);

    public static Date parse(String strDate) throws ParseException {
        return sSimpleDateFormat.parse(strDate);
    }

    /**
     * 计算当前时间差
     * @param toTime
     * @return
     */
    public static String getGapTime(String toTime) {
        String time = day;
        if (StringUtils.isEmpty(toTime)) {
            return time;
        }
        long from;
        try {
            from = parse(toTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return time;
        }
        long gap = sNowTime.getTime() - from;
        long day = gap / nd;
        int hours = (int) (gap / nh);
        if (day > 1) {
            return day + ResourceUtil.getString(R.string.before_day);
        }
        if (hours > 0 && hours < 24) {
            return hours + ResourceUtil.getString(R.string.before_hours);
        }
        return time;
    }

}
