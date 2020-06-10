package com.xh.module.base.entity;

import java.io.Serializable;

/**
 * table name:  role
 * author name: 朱华清
 * create time: 2020-05-04 17:28:01
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 969773967512404190L;

    private Long id;//ID
    private String name;//角色名称
    private Integer createTime;//创建时间
    private Integer type;
    private Long school_id;
    private String cla_id; // 如果是老师。并且有多个班级  用,分割

    public Role() {
        super();
    }

    public Role(Long id, String name, Integer createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Long school_id) {
        this.school_id = school_id;
    }

    public String getCla_id() {
        return cla_id;
    }

    public void setCla_id(String cla_id) {
        this.cla_id = cla_id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", type=" + type +
                ", school_id='" + school_id + '\'' +
                ", cla_id='" + cla_id + '\'' +
                '}';
    }
}

