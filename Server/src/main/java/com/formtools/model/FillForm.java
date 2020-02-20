package com.formtools.model;

/**
 * 用户填过哪些表单
 * @author myl
 * @create 2020-02-05  16:59
 */
public class FillForm {
    //自增id，主键
    private Integer id;
    //填表者id
    private String userId;
    //所填的表的id
    private String formId;

    public FillForm() {
    }

    public FillForm( String userId, String formId) {
        this.userId = userId;
        this.formId = formId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"formId\":\"")
                .append(formId).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
