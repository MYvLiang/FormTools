package com.formtools.controller;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import com.alibaba.fastjson.JSONObject;
import com.formtools.OtherConfig;
import com.formtools.model.UserModel;
import com.formtools.service.OtherUserService;
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
    private OtherUserService otherUserService;

    private final String APP_ID= OtherConfig.APP_ID;
    private final String APP_SEC=OtherConfig.APP_SEC;
    private final String BACK_URL=OtherConfig.BACK_URL;
    private final String DEFAULTREDIRECTURL=OtherConfig.DEFAULTREDIRECTURL;

    @RequestMapping("/login")
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
            otherUserService.updateUser(userModel);

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
