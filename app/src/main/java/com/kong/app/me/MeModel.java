package com.kong.app.me;

/**
 * Created by CaoPengfei on 17/7/27.
 */

public class MeModel {
    public int type;
    public int icon;
    public String name;

    public MeModel(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public MeModel(int type, int icon, String name) {
        this.type = type;
        this.icon = icon;
        this.name = name;
    }
}
