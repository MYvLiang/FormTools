package com.formtools.model;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author myl
 * @create 2020-04-04  13:53
 */
@ApiModel("非问卷类表单收集的信息")
public class FillRegistry implements Serializable {

    private Integer id;
    @NotNull
    private Long userId;
    @NotNull
    private Long formId;
    @ApiModelProperty("用户填写的信息")
    private JSONObject fillContent;
    @ApiModelProperty("用户填写完成的时间")
    private Timestamp fillTime;
    @ApiModelProperty("用户最后一次修改的时间")
    private Timestamp alterTime;
    @ApiModelProperty("附件列表，每个附件的url")
    private String fileList;
    @ApiModelProperty("审核类表单的状态")
    private Character checkState;

    public FillRegistry() {
    }

    public FillRegistry(Long userId, Long formId, JSONObject fillContent, Timestamp fillTime, Timestamp alterTime, String fileList, Character checkState) {
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

    public Character getCheckState() {
        return checkState;
    }

    public void setCheckState(Character checkState) {
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
