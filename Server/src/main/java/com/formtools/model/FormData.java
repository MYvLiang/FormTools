package com.formtools.model;

import java.sql.Timestamp;

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
    private String fillInfo;
    //填表时间
    private Timestamp fillTime;
    //最后一次修改时间
    private Timestamp alterTime;

    public FormData() {
    }

    public FormData(String userId, String fillInfo, Timestamp fillTime, Timestamp alterTime) {
        this.userId = userId;
        this.fillInfo = fillInfo;
        this.fillTime = fillTime;
        this.alterTime = alterTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFillInfo() {
        return fillInfo;
    }

    public void setFillInfo(String fillInfo) {
        this.fillInfo = fillInfo;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"fillInfo\":\"")
                .append(fillInfo).append('\"');
        sb.append(",\"fillTime\":\"")
                .append(fillTime).append('\"');
        sb.append(",\"alterTime\":\"")
                .append(alterTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
