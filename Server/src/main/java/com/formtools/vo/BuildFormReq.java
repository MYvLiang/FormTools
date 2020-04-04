package com.formtools.vo;

import com.alibaba.fastjson.JSONObject;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author myl
 * @create 2020-04-04  21:24
 */
public class BuildFormReq {
    @NotNull
    private String formTitle;
    @NotNull
    private JSONObject formInfo;
    @NotNull
    private Timestamp beginTime;
    @NotNull
    private Timestamp endTime;
    @NotNull
    private Integer maxCount;
    @NotNull
    private Character formType;

    public BuildFormReq() {
    }

    public BuildFormReq(String formTitle, JSONObject formInfo, Timestamp beginTime, Timestamp endTime, Integer maxCount, Character formType) {
        this.formTitle = formTitle;
        this.formInfo = formInfo;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.maxCount = maxCount;
        this.formType = formType;
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

    public Character getFormType() {
        return formType;
    }

    public void setFormType(Character formType) {
        this.formType = formType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"formTitle\":\"")
                .append(formTitle).append('\"');
        sb.append(",\"formInfo\":")
                .append(formInfo);
        sb.append(",\"beginTime\":\"")
                .append(beginTime).append('\"');
        sb.append(",\"endTime\":\"")
                .append(endTime).append('\"');
        sb.append(",\"maxCount\":")
                .append(maxCount);
        sb.append(",\"formType\":")
                .append(formType);
        sb.append('}');
        return sb.toString();
    }
}
