package com.xh.module.base.entity;

public class TeacherClass {


    /**
     * teacherId : 715679245308788736
     * courseId : 3
     * clasId : 715679245375897600
     * course : {"id":"3","courseName":"英语","type":1}
     * clas : {"id":"715679245375897600","name":"7班","schoolId":"715153984603553792","gradeName":"三年级","headmasterId":"0","createTime":1590672828,"updateTime":1590672828}
     */

    private String teacherId;
    private String courseId;
    private String clasId;
    private Course course;
    private Clas clas;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getClasId() {
        return clasId;
    }

    public void setClasId(String clasId) {
        this.clasId = clasId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Clas getClas() {
        return clas;
    }

    public void setClas(Clas clas) {
        this.clas = clas;
    }
}
