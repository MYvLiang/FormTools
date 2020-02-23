package com.formtools.vo;

/**
 * @author myl
 * @create 2020-02-23  21:36
 */
public class WeiXinUser {
    //微信小程序用户的openid
    private String openid;
    //小程序码的参数
    private String scene;
    //微信昵称
    private String nickName;
    //微信头像
    private String avatarUrl;

    public WeiXinUser() {
    }

    public WeiXinUser(String openid, String scene, String nickName, String avatarUrl) {
        this.openid = openid;
        this.scene = scene;
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"openid\":\"")
                .append(openid).append('\"');
        sb.append(",\"scene\":\"")
                .append(scene).append('\"');
        sb.append(",\"nickName\":\"")
                .append(nickName).append('\"');
        sb.append(",\"avatarUrl\":\"")
                .append(avatarUrl).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
