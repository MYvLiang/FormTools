package com.formtools.controller;

import com.formtools.model.UserModel;
import com.formtools.service.OtherUserService;
import com.formtools.utils.WeiXinCodeUtil;
import com.formtools.vo.WeiXinCode;
import com.formtools.vo.WeiXinUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;


/**
 * @author myl
 * @create 2020-02-23  21:39
 */
@RestController
@RequestMapping("/weixin")
public class WeiXinLoginController {
    @Autowired
    private OtherUserService otherUserService;

    private static ConcurrentHashMap<String, String> wxSceneMap=new ConcurrentHashMap();
    /**
     * 返回小程序二维码的url和scene，二维码使用参数scene生成
     *
     * @return
     */
    @GetMapping("/code")
    public WeiXinCode getwxCode() {
        String scene = "scene" + System.currentTimeMillis();
        wxSceneMap.put(scene, "on");
        WeiXinCode weiXinCode = new WeiXinCode();
        weiXinCode.setCodeURL(WeiXinCodeUtil.getCodeURL(scene));
        weiXinCode.setScene(scene);
        return weiXinCode;
    }

    /**
     * 返回no即小程序端未授权登录
     * 返回yes即小程序端已授权登录
     *
     * @param scene
     * @return
     */
    @GetMapping("/isLogin")
    public String isLogin(@RequestParam String scene) {
        return wxSceneMap.get(scene);
    }

    @PostMapping("/login")
    public String wxLogin(@RequestBody WeiXinUser wxUser) {
        UserModel userModel = new UserModel(wxUser.getOpenid(),
                wxUser.getNickName(), wxUser.getAvatarUrl());
        if(otherUserService.updateUser(userModel)){
            wxSceneMap.put(wxUser.getScene(), "yes");
        }
        return "success";
    }
}
