package com.formtools.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * @author myl
 * @create 2020-04-04  10:45
 */
public class FillQuestionnaire {
    //自增id
    private Integer id;
    //表单id
    private Long formId;
    //表单收集的数据
    private JSONObject fillContent;
    //填表时间
    private Timestamp fillTime;

    public FillQuestionnaire() {
    }

    public FillQuestionnaire(Long formId, JSONObject fillContent, Timestamp fillTime) {
        this.formId = formId;
        this.fillContent = fillContent;
        this.fillTime = fillTime;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"formId\":\"")
                .append(formId).append('\"');
        sb.append(",\"fillContent\":\"")
                .append(fillContent).append('\"');
        sb.append(",\"fillTime\":\"")
                .append(fillTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
