package com.formtools.vo;

/**
 * @author myl
 * @create 2020-02-25  23:44
 */
public class WXToken {
    private String access_token;
    private Integer expires_in;
    private Integer errcode;
    private String errmsg;

    public WXToken() {
    }

    public WXToken(String access_token, Integer expires_in, Integer errcode, String errmsg) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"access_token\":\"")
                .append(access_token).append('\"');
        sb.append(",\"expires_in\":\"")
                .append(expires_in).append('\"');
        sb.append(",\"errcode\":\"")
                .append(errcode).append('\"');
        sb.append(",\"errmsg\":\"")
                .append(errmsg).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
