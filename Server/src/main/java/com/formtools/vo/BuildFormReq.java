package com.formtools.vo;

import com.alibaba.fastjson.JSONObject;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author myl
 * @create 2020-04-04  21:24
 */
public class BuildFormReq {

    private Long formId;
    @NotNull
    private String formTitle;
    @NotNull
    private JSONObject formInfo;

    private Timestamp builtTime;

    private Timestamp beginTime;

    private Timestamp endTime;

    private Integer maxCount;
    @NotNull
    private String formType;

    private Short state;

    public BuildFormReq() {
    }

    public BuildFormReq(Long formId, String formTitle, JSONObject formInfo, Timestamp builtTime, Timestamp beginTime, Timestamp endTime, Integer maxCount,  String formType, Short state) {
        this.formId = formId;
        this.formTitle = formTitle;
        this.formInfo = formInfo;
        this.builtTime = builtTime;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.maxCount = maxCount;
        this.formType = formType;
        this.state = state;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public JSONObject getFormInfo() {
        return formInfo;
    }

    public void setFormInfo(JSONObject formInfo) {
        this.formInfo = formInfo;
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

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Timestamp getBuiltTime() {
        return builtTime;
    }

    public void setBuiltTime(Timestamp builtTime) {
        this.builtTime = builtTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"formId\":")
                .append(formId);
        sb.append(",\"formTitle\":\"")
                .append(formTitle).append('\"');
        sb.append(",\"formInfo\":")
                .append(formInfo);
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
