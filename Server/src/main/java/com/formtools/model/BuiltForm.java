package com.formtools.model;

import java.sql.Timestamp;

/**
 * 用户创建的表单，
 * 包含表单id、建表者id、储存表结构信息的JSON串
 * @author myl
 * @create 2020-02-05  14:13
 */
public class BuiltForm {
    //自增id
    private Integer id;
    //表单id
    private String formId;
    //建表者id
    private String userId;
    //表单标题
    private String formTitle;
    //表单结构，是一个json串
    private String formInfo;
    //建表时间
    private Timestamp builtTime;
    //开始填表时间（默认为建表时间）
    private Timestamp beginTime;
    //截止时间（可无）
    private Timestamp endTime;
    //最多可填表的人数（可无）
    private Integer maxCount;
    //表单状态（是否截止）
    private Character formState;
    //表单类型
    private Character formType;

    public BuiltForm() {
    }

    public BuiltForm(String formId, String userId, String formTitle, String formInfo, Timestamp builtTime, Timestamp beginTime, Timestamp endTime, Integer maxCount, Character formState, Character formType) {
        this.formId = formId;
        this.userId = userId;
        this.formTitle = formTitle;
        this.formInfo = formInfo;
        this.builtTime = builtTime;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.maxCount = maxCount;
        this.formState = formState;
        this.formType = formType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getFormInfo() {
        return formInfo;
    }

    public void setFormInfo(String formInfo) {
        this.formInfo = formInfo;
    }

    public Timestamp getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(Timestamp builtTime) {
        this.builtTime = builtTime;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Character getFormState() {
        return formState;
    }

    public void setFormState(Character formState) {
        this.formState = formState;
    }

    public Character getFormType() {
        return formType;
    }

    public void setFormType(Character formType) {
        this.formType = formType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"formId\":\"")
                .append(formId).append('\"');
        sb.append(",\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"formTitle\":\"")
                .append(formTitle).append('\"');
        sb.append(",\"formInfo\":\"")
                .append(formInfo).append('\"');
        sb.append(",\"builtTime\":\"")
                .append(builtTime).append('\"');
        sb.append(",\"beginTime\":\"")
                .append(beginTime).append('\"');
        sb.append(",\"endTime\":\"")
                .append(endTime).append('\"');
        sb.append(",\"maxCount\":")
                .append(maxCount);
        sb.append(",\"formState\":")
                .append(formState);
        sb.append(",\"formType\":")
                .append(formType);
        sb.append('}');
        return sb.toString();
    }
}
