package com.formtools.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author myl
 * @create 2020-04-04  13:53
 */
public class FillRegistry implements Serializable {

    private Integer id;
    private Long userId;
    private Long formId;
    private String fillContent;
    private Timestamp fillTime;
    private Timestamp alterTime;
    private String fileList;
    private String checkState;

    public FillRegistry() {
    }

    public FillRegistry(Long userId, Long formId, String fillContent, Timestamp fillTime, Timestamp alterTime, String fileList, String checkState) {
        this.userId = userId;
        this.formId = formId;
        this.fillContent = fillContent;
        this.fillTime = fillTime;
        this.alterTime = alterTime;
        this.fileList = fileList;
        this.checkState = checkState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getFillContent() {
        return fillContent;
    }

    public void setFillContent(String fillContent) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"formId\":")
                .append(formId);
        sb.append(",\"fillContent\":\"")
                .append(fillContent).append('\"');
        sb.append(",\"fillTime\":\"")
                .append(fillTime).append('\"');
        sb.append(",\"alterTime\":\"")
                .append(alterTime).append('\"');
        sb.append(",\"fileList\":")
                .append(fileList);
        sb.append(",\"checkState\":")
                .append(checkState);
        sb.append('}');
        return sb.toString();
    }
}
