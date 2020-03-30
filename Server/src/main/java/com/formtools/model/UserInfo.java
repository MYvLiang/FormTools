package com.formtools.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * @author myl
 * @create 2020-03-27  11:18
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserInfo {
    public interface register extends Default {}

    //用户id
    private Long userId;

    @NotNull(groups = UserInfo.register.class)
    @NotEmpty(groups = UserInfo.register.class)
    //昵称
    private String nickname;

    //头像
    private String profile;

    public UserInfo() {
    }

    public UserInfo(Long userId, String nickname, String profile) {
        this.userId = userId;
        this.nickname = nickname;
        this.profile = profile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"nickname\":\"")
                .append(nickname).append('\"');
        sb.append(",\"profile\":\"")
                .append(profile).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
