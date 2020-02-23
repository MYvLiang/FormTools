package com.formtools.vo;

/**
 * @author myl
 * @create 2020-02-23  22:17
 */
public class WeiXinCode {
    private String CodeURL;
    private String scene;

    public WeiXinCode() {
    }

    public WeiXinCode(String codeURL, String scene) {
        CodeURL = codeURL;
        this.scene = scene;
    }

    public String getCodeURL() {
        return CodeURL;
    }

    public void setCodeURL(String codeURL) {
        CodeURL = codeURL;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"CodeURL\":\"")
                .append(CodeURL).append('\"');
        sb.append(",\"scene\":\"")
                .append(scene).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
