package com.formtools;

/**
 * @author myl
 * @create 2020-02-27  15:48
 */
public class OtherConfig {
    //易班配置
//    public static final String APP_ID = "abb9a6549cddf5fc";
//    public static final String APP_SEC = "1c89782f669e34bf1fbdccca2a33be1a";
//    public static final String BACK_URL = "http://api.shinytengxvnyun.cn/YBAPI/auth";
//    public static final String DEFAULTREDIRECTURL = "http://shinytengxvnyun.cn";
    //易班开发测试环境的配置
    public static final String APP_ID = "985f6bf0a2b04632";
    public static final String APP_SEC = "413ee0b76cb3d8e00e84ab5c2c24f100";
    public static final String BACK_URL = "http://localhost:8080/YBAPI/auth";
    public static final String DEFAULTREDIRECTURL = "http://shinytengxvnyun.cn";

    //微信小程序配置
    public static final String GRANT_TYPE = "client_credential";
    public static final String APPID = "wx80e741529e832e6b";
    public static final String SECRET = "4e1e6771f9c2ca3cee6edcee2306921f";
    public static final String LOGIN_PAGE="pages/webLogin/webLogin";
    public static final String TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=";
    public static final String CODE_URL="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";

    //部署域名
    public static final String APPURL="http://api.shinytengxvnyun.cn";

    public static int cookieMaxAge=60*60*24*365;
}
