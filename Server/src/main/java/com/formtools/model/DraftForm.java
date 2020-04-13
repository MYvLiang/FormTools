package com.formtools.model;

import java.sql.Timestamp;

/**
 * @author myl
 * @create 2020-04-13  11:56
 */
public class DraftForm {
    //自增id
    private Integer id;
    //表单id
    private Long formId;
    //建表者id
    private Long userId;
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
    //表单类型
    private String formType;
    //redis的数据是否持久化到该表
    private Boolean state;

    public DraftForm(){

    }

    public DraftForm(Long formId, Long userId, String formTitle, String formInfo, Timestamp builtTime, Timestamp beginTime, Timestamp endTime, Integer maxCount, String formType, Boolean state) {
        this.formId = formId;
        this.userId = userId;
        this.formTitle = formTitle;
        this.formInfo = formInfo;
        this.builtTime = builtTime;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.maxCount = maxCount;
        this.formType = formType;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"formId\":")
                .append(formId);
        sb.append(",\"userId\":")
                .append(userId);
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
        sb.append(",\"formType\":")
                .append(formType);
        sb.append(",\"state\":")
                .append(state);
        sb.append('}');
        return sb.toString();
    }
}
