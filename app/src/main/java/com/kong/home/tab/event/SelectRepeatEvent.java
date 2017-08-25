package com.kong.home.tab.event;

/**
 * Created by CaoPengfei on 17/8/25.
 */

public class SelectRepeatEvent {
    public static final int HOMEINDEX = 0;
    public static final int BLOGINDEX = 1;
    public static final int GANKINDEX = 2;
    public int type;

    public SelectRepeatEvent(int type) {
        this.type = type;
    }
}
