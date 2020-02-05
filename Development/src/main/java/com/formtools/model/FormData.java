package com.formtools.model;

/**
 * 用户创建的表单所收集的数据
 *
 * @author myl
 * @create 2020-02-05  17:26
 */
public class FormData {

    //用户id
    private String userId;

    //表单收集的数据，是一个json串
    private String data;

    public FormData() {
    }

    public FormData(String userId, String data) {
        this.userId = userId;
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FormData{" +
                "userId='" + userId + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
