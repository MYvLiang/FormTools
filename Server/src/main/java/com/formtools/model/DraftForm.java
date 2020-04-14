package com.formtools.model;

import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;

/**
 * @author myl
 * @create 2020-04-13  11:56
 */
public class DraftForm extends BuiltForm{
    //redis的数据是否持久化到该表
    private Boolean state;

    public DraftForm(){
        super();
    }

    public DraftForm(Long formId, Long userId, String formTitle, JSONObject formInfo, Timestamp builtTime, Timestamp beginTime, Timestamp endTime, Integer maxCount, String formType, Boolean state) {
        super(formId,userId,formTitle,formInfo,builtTime,beginTime,endTime,maxCount,formType);
        this.state = state;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"buildForm\":")
                .append(super.toString());
        sb.append(",\"state\":")
                .append(state);
        sb.append('}');
        return sb.toString();
    }
}
