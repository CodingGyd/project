package com.gyd.rookiepalmspace.base.entity;

import java.util.List;

/**
 * Created by wgyscsf on 2016/5/7.
 */
public class Sentence {
    private int sc;
    private boolean ls;
    private int bg;
    private int ed;
    private List<Ws> ws;

    public int getSc() {
        return sc;
    }

    public void setSc(int sc) {
        this.sc = sc;
    }

    public boolean isLs() {
        return ls;
    }

    public void setLs(boolean ls) {
        this.ls = ls;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getEd() {
        return ed;
    }

    public void setEd(int ed) {
        this.ed = ed;
    }

    public List<Ws> getWs() {
        return ws;
    }

    public void setWs(List<Ws> ws) {
        this.ws = ws;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "sc=" + sc +
                ", ls=" + ls +
                ", bg=" + bg +
                ", ed=" + ed +
                ", ws=" + ws +
                '}';
    }
}
