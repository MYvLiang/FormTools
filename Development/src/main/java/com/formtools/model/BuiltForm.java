package com.formtools.model;

/**
 * 用户创建的表单
 * @author myl
 * @create 2020-02-05  14:13
 */
public class BuiltForm {
    //表id
    private String id;
    //建表者id
    private String builderId;
    //表结构，是一个json串
    private String formStructure;

    public BuiltForm() {
    }

    public BuiltForm(String id, String builderId, String formStructure) {
        this.id = id;
        this.builderId = builderId;
        this.formStructure = formStructure;
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
        return formStructure;
    }

    public void setFormStructure(String formStructure) {
        this.formStructure = formStructure;
    }

    @Override
    public String toString() {
        return "BuiltForm{" +
                "id='" + id + '\'' +
                ", builderId='" + builderId + '\'' +
                ", formStructure='" + formStructure + '\'' +
                '}';
    }
}
