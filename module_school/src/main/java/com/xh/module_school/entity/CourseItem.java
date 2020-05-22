package com.xh.module_school.entity;

/**
 * 课程表选项
 */
public class CourseItem {

    /**
     * 时间
     */
    private String time;
    /**
     * 名字
     */
    private String name;

    public CourseItem(String time, String name) {
        this.time = time;
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
