package com.formtools.model;

/**
 * 用户创建的表单，
 * 包含表单id、建表者id、储存表结构信息的JSON串
 * @author myl
 * @create 2020-02-05  14:13
 */
public class BuiltForm {
    //表单id
    private String id;
    //建表者id
    private String builderId;
    //表结构，是一个json串
    private String formInfo;

    public BuiltForm() {
    }

    public BuiltForm(String id, String builderId, String formInfo) {
        this.id = id;
        this.builderId = builderId;
        this.formInfo = formInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuilderId() {
        return builderId;
    }

    public void setBuilderId(String builderId) {
        this.builderId = builderId;
    }

    public String getFormStructure() {
        return formInfo;
    }

    public void setFormStructure(String formInfo) {
        this.formInfo = formInfo;
    }

    @Override
    public String toString() {
        return "BuiltForm{" +
                "id='" + id + '\'' +
                ", builderId='" + builderId + '\'' +
                ", formInfo='" + formInfo + '\'' +
                '}';
    }
}
