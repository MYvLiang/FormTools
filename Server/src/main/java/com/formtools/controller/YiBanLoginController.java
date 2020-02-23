package com.formtools.controller;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import com.alibaba.fastjson.JSONObject;
import com.formtools.model.UserModel;
import com.formtools.service.IOtherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author myl
 * @create 2020-02-23  23:02
 */
@Controller
@RequestMapping("/YBAPI")
public class YiBanLoginController {
    @Autowired
    private IOtherUserService iOtherUserService;

    private final String APP_ID="985f6bf0a2b04632";
    private final String APP_SEC="413ee0b76cb3d8e00e84ab5c2c24f100";
    private final String BACK_URL="http://localhost:8080/YBAPI/auth";
    private final String DEFAULTREDIRECTURL="http://shinytengxvnyun.cn";

    @RequestMapping("/yb")
    public String yblogin(@RequestParam(value = "state",
            defaultValue = DEFAULTREDIRECTURL)String state){
        Authorize authorize=new Authorize(APP_ID,APP_SEC);
        String url = authorize.forwardurl(BACK_URL,state, Authorize.DISPLAY_TAG_T.WEB);
        return "redirect:"+url;
    }

    @RequestMapping("/auth")
    public String  ybauth(@RequestParam(value = "code",required = false)String code,
                          @RequestParam(value = "state",
                                  defaultValue = DEFAULTREDIRECTURL)String redirectURL,
                          HttpServletResponse response){
        if(code!=null){
            Authorize authorize = new Authorize(APP_ID,APP_SEC );
            String text = authorize.querytoken(code, BACK_URL);
            JSONObject textjson = JSONObject.parseObject(text);
            if(!textjson.containsKey("access_token")){
                return "redirect:"+DEFAULTREDIRECTURL;
            }
            String token=textjson.getString("access_token");
            User user=new User(token);
            String metext = user.me();
            if(metext==null){
                return "redirect:"+DEFAULTREDIRECTURL;
            }
            JSONObject me=JSONObject.parseObject(metext);
            if(!me.containsKey("status")||!me.getString("status").equals("success")){
                return "redirect:"+DEFAULTREDIRECTURL;
            }
            JSONObject info=me.getJSONObject("info");
            String userId=info.getString("yb_userid");
            String nickname=info.getString("yb_username");
            String profile=info.getString("yb_userhead");

            UserModel userModel=new UserModel(userId,nickname,profile);
            iOtherUserService.updateUser(userModel);

            Cookie uesrIdCookie=new Cookie("uesrId",userId);
            uesrIdCookie.setMaxAge(7*24*60*60);
            uesrIdCookie.setPath("/");
            uesrIdCookie.setHttpOnly(true);
            response.addCookie(uesrIdCookie);
            return "redirect:"+redirectURL;
        }
        return "redirect:"+DEFAULTREDIRECTURL;
    }
}
