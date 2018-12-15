package com.baselib.utlis;

/**
 * Created by CaoPengfei on 17/8/25.
 */

public class DoubleTool {

    private long firstBackPressedTime = 0;

    public boolean doubleClickKeyEvent() {
        long secondBackPressedTime = System.currentTimeMillis();
        if (secondBackPressedTime - firstBackPressedTime > 2000) {
            firstBackPressedTime = secondBackPressedTime;
            return true;
        }
        return false;
    }
}
