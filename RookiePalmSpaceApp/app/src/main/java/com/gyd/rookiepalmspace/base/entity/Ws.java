package com.gyd.rookiepalmspace.base.entity;

import java.util.List;

/**
 * Created by wgyscsf on 2016/5/7.
 */
public class Ws {
    private int bg;
    private List<Cw> cw;

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public List<Cw> getCw() {
        return cw;
    }

    public void setCw(List<Cw> cw) {
        this.cw = cw;
    }

    @Override
    public String toString() {
        return "Ws{" +
                "bg=" + bg +
                ", cw=" + cw +
                '}';
    }
}
