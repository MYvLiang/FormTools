package com.formtools.vo;

import com.alibaba.fastjson.JSONObject;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class FillRegistryReq {
    @NotNull
    private Long userId;
    @NotNull
    private Long formId;
    private JSONObject fillContent;
    private Timestamp fillTime;
    private Timestamp alterTime;
    private String fileList;
    private String checkState;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public JSONObject getFillContent() {
        return fillContent;
    }

    public void setFillContent(JSONObject fillContent) {
        this.fillContent = fillContent;
    }

    public Timestamp getFillTime() {
        return fillTime;
    }

    public void setFillTime(Timestamp fillTime) {
        this.fillTime = fillTime;
    }

    public Timestamp getAlterTime() {
        return alterTime;
    }

    public void setAlterTime(Timestamp alterTime) {
        this.alterTime = alterTime;
    }

    public String getFileList() {
        return fileList;
    }

    public void setFileList(String fileList) {
        this.fileList = fileList;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public FillRegistryReq() {
    }

    public FillRegistryReq(Long userId, Long formId, JSONObject fillContent, Timestamp fillTime, Timestamp alterTime, String fileList, String checkState) {
        this.userId = userId;
        this.formId = formId;
        this.fillContent = fillContent;
        this.fillTime = fillTime;
        this.alterTime = alterTime;
        this.fileList = fileList;
        this.checkState = checkState;
    }
}
