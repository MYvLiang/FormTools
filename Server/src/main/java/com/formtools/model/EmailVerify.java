package com.formtools.model;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author myl
 * @create 2020-03-27  11:41
 */
@ApiModel("邮箱登录验证")
public class EmailVerify {
    @Null
    private Integer id;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    public EmailVerify() {
    }

    public EmailVerify(@Null Integer id, @NotNull @NotEmpty String email, @NotNull @NotEmpty String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
