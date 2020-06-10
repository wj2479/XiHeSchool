package com.xh.module.base.entity.pay;

/**
 * 付款项目
 * table name:  pay_item
 * author name: 朱华清
 * create time: 2020-06-09 20:30:00
 */
public class PayItem {

    //	@Column(columnName = "id",allowNull = false,comment = "主键")
    private Long id;

    //	@Column(columnName = "school_id",allowNull = false,comment = "学校id")
    private Long schoolId;

    //	@Column(columnName = "clas_id",allowNull = true,comment = "班级id（如果是学校发起可为空）")
    private Long clasId;

    //	@Column(columnName = "type",allowNull = false,comment = "0:家委会发起 1:学校发起")
    private Integer type;

    //	@Column(columnName = "title",allowNull = false,comment = "标题")
    private String title;

    //	@Column(columnName = "content",allowNull = false,comment = "内容")
    private String content;

    //	@Column(columnName = "create_uid",allowNull = false,comment = "创建人uid")
    private Long createUid;

    //	@Column(columnName = "create_time",allowNull = false,comment = "创建时间")
    private Integer createTime;

    public PayItem() {
        super();
    }

    public PayItem(Long id, Long schoolId, Long clasId, Integer type, String title, String content, Long createUid, Integer createTime) {
        this.id = id;
        this.schoolId = schoolId;
        this.clasId = clasId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.createUid = createUid;
        this.createTime = createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setClasId(Long clasId) {
        this.clasId = clasId;
    }

    public Long getClasId() {
        return clasId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "pay_item[" +
                "id=" + id +
                ", school_id=" + schoolId +
                ", clas_id=" + clasId +
                ", type=" + type +
                ", title=" + title +
                ", content=" + content +
                ", create_uid=" + createUid +
                ", create_time=" + createTime +
                "]";
    }
}

