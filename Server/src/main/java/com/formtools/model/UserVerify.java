package com.formtools.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author myl
 * @create 2020-03-27  11:27
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserVerify {
    @Null
    private Integer id;

    private Long userId;

    @NotNull
    @NotEmpty
    private String openid;

    private Character type;

    public UserVerify() {
    }

    public UserVerify(@Null Integer id, Long userId, @NotNull @NotEmpty String openid, Character type) {
        this.id = id;
        this.userId = userId;
        this.openid = openid;
        this.type = type;
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"openid\":\"")
                .append(openid).append('\"');
        sb.append(",\"type\":")
                .append(type);
        sb.append('}');
        return sb.toString();
    }
}
