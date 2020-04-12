package com.formtools.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.formtools.enums.ErrorMsg;
import com.formtools.model.BuiltForm;
import com.formtools.model.FillRegistry;
import com.formtools.service.FillRegistryService;
import com.formtools.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@RestController
public class FillRegistryController {

    @Resource
    private FillRegistryService fillRegistryService;

    /**
     * 获取表单题目
     *
     * @param id 表单id
     * @return vo
     */
    @GetMapping("/form-question-info")
    public ResultVo getFormQuestionInfo(@RequestParam("form-id") String id) {
        long formId;
        try {
            formId = Long.parseLong(id);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.FORM_NUMBER_ERROR);
        }
        BuiltForm builtForm = fillRegistryService.getFormInfo(formId);
        if (builtForm == null) {
            return ResultVo.fail(ErrorMsg.FORM_NUMBER_ERROR);
        }
        return ResultVo.success(builtForm);
    }

    @PostMapping("/current-save")
    public ResultVo currentSaveAnswer(@RequestBody String fill, HttpServletRequest request, HttpServletResponse response) {
        //优化*****************************
        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.parseObject(fill);
        } catch (Exception e) {
            //参数解析错误
            return ResultVo.fail(ErrorMsg.JSON_READ_ERROR);
        }
        //将前端的fillContent的Object类型转化为String
        //憨憨代码
        String fillContent= JSON.toJSONString(jsonObject.get("fillContent"));
        FillRegistry fillRegistry;
        try {
            fillRegistry = new FillRegistry(
                    Long.parseLong(jsonObject.get("userId").toString()),
                    Long.parseLong(jsonObject.get("formId").toString()),
                    fillContent,
                    (Timestamp)jsonObject.get("fillTime"),
                    (Timestamp)jsonObject.get("alterTime"),
                    (String)jsonObject.get("fileList"),
                    (Character)jsonObject.get("checkState")
            );
        } catch (NumberFormatException e) {
            return ResultVo.fail(ErrorMsg.PARAM_ERROR);
        }
        //优化******************************************

        String key = null;
        String formId=fillRegistry.getFormId().toString();
        //遍历cookie 获取名为formId的cookie 内含缓存的key
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(formId)) {
                key = cookie.getValue();
                break;
            }
        }
        try {
            //取得缓存的key
            key = fillRegistryService.currentSaveAnswer(key, fillRegistry);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        //写入cookie
        Cookie cookie = new Cookie(formId, key);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
        return ResultVo.success();
    }
}
