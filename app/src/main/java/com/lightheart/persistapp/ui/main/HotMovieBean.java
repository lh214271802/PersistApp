package com.lightheart.persistapp.ui.main;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author LiaoHui
 * @date 2019/1/25
 * @desc
 */
public class HotMovieBean {
    @SerializedName("count")
    private int count;
    @SerializedName("start")
    private int start;
    @SerializedName("total")
    private int total;
    @SerializedName("title")
    private String title;
    @SerializedName("subjects")
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public int getStart() {
        return start;
    }

    public int getTotal() {
        return total;
    }

    public String getTitle() {
        return title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "HotMovieBean{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
