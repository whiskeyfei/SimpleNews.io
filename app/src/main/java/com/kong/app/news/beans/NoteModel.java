package com.kong.app.news.beans;

import java.io.Serializable;

/**
 * Created by CaoPengfei on 17/6/9.
 */

public class NoteModel implements Serializable {
    public int id;
    public String name;

    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
