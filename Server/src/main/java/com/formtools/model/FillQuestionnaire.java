package com.formtools.model;

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
    //表单收集的数据，是一个json串
    private String fillContent;
    //填表时间
    private Timestamp fillTime;

    public FillQuestionnaire() {
    }

    public FillQuestionnaire(Long formId, String fillContent, Timestamp fillTime) {
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
