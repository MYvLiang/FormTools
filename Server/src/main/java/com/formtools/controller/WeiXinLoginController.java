package com.formtools.controller;

import com.formtools.OtherConfig;
import com.formtools.enums.ErrorMsg;
import com.formtools.model.UserModel;
import com.formtools.service.OtherUserService;
import com.formtools.utils.WeiXinCodeUtil;
import com.formtools.vo.ResultVo;
import com.formtools.vo.WeiXinUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author myl
 * @create 2020-02-23  21:39
 */
@RestController
@RequestMapping("/weixin")
@Validated
public class WeiXinLoginController {
    @Autowired
    private OtherUserService otherUserService;

    private static ConcurrentHashMap<String, String> wxSceneMap = new ConcurrentHashMap();
    /**
     * 返回小程序二维码和scene，二维码使用参数scene生成
     *
     * @return
     */
    @GetMapping(value = "/code", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getwxCode(HttpServletResponse response) {
        String scene = "scene" + System.currentTimeMillis();
        byte[] code = WeiXinCodeUtil.getCode(scene);
        wxSceneMap.put(scene, "no");
        response.setHeader("scene", scene);
        return code;
    }

    /**
     * 检验小程序中是否授权登录
     * 返回no即小程序端未授权登录
     * 返回yes即小程序端已授权登录
     *
     * @param scene
     * @return
     */
    @GetMapping("/isLogin")
    public ResultVo isLogin(@RequestParam String scene,HttpServletResponse response) {
        String sceneState = wxSceneMap.get(scene);
        if(sceneState==null){
            return ResultVo.fail(ErrorMsg.PARAM_ERROR,"该scene不存在");
        }else if (!sceneState.equals("no")) {
            Cookie uesrIdCookie=new Cookie("userId",sceneState);
            uesrIdCookie.setMaxAge(OtherConfig.cookieMaxAge);
            uesrIdCookie.setPath("/");
            uesrIdCookie.setHttpOnly(true);
            response.addCookie(uesrIdCookie);
            wxSceneMap.remove(scene);
            return ResultVo.success("success");
        }
        return ResultVo.success("no");
    }

    /**
     * 小程序中调用该接口进行登录
     * @param wxUser
     * @return
     */
    @PostMapping("/login")
    public ResultVo wxLogin(@RequestBody @Valid WeiXinUser wxUser) {
        Long userId=otherUserService.updateUser(wxUser.getNickName(),wxUser.getAvatarUrl(),wxUser.getOpenid());
        if(userId!=null){
            wxSceneMap.put(wxUser.getScene(),String.valueOf(userId));
            return ResultVo.success("success");
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR,"更新或添加用户信息时出错");
    }
}
