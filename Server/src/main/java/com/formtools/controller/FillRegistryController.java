package com.formtools.controller;

import com.formtools.enums.ErrorMsg;
import com.formtools.model.BuiltForm;
import com.formtools.model.FillRegistry;
import com.formtools.service.FillRegistryService;
import com.formtools.utils.CookieUtil;
import com.formtools.utils.FillRegisteryUtil;
import com.formtools.vo.FillRegistryReq;
import com.formtools.vo.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    /**
     * 填表实时保存
     *
     * @param fillRegistryReq  答案
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return 成功（无数据
     */
    @PostMapping("/current-save")
    public ResultVo currentSaveAnswer(@RequestBody FillRegistryReq fillRegistryReq, HttpServletRequest request, HttpServletResponse response) {

        //转化
        FillRegistry fillRegistry= FillRegisteryUtil.toFillRegisteryCommen(fillRegistryReq);
        String formId=fillRegistry.getFormId().toString();
        //获取缓存key
        String key = CookieUtil.getKeyFromFormIdCookie(request,formId);
        try {
            //取得缓存的key
            key = fillRegistryService.currentSaveAnswer(key, fillRegistry);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        //写入cookie
        Cookie cookie = new Cookie(formId, key);
        cookie.setHttpOnly(true);
        //设置过期时间为7天
        cookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cookie);
        return ResultVo.success();
    }

    /**
     * 获取答案
     *
     * @param uid     用户id（从cookie获取
     * @param request HttpServletRequest
     * @param fId     表id
     * @return 答案
     */
    @GetMapping("/filled")
    public ResultVo getFilledInfo(@CookieValue("userId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String uid, HttpServletRequest request,
                                  @RequestParam("formId") String fId) {
        Long userId;
        Long formId;
        try {
            userId = Long.parseLong(uid);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        try {
            formId = Long.parseLong(fId);
        } catch (NumberFormatException e) {
            return ResultVo.fail(ErrorMsg.FORM_NUMBER_ERROR);
        }

        //获取缓存key
        String key = CookieUtil.getKeyFromFormIdCookie(request,fId);

        FillRegistry fillRegistry;
        try {
            fillRegistry = fillRegistryService.getAnswer(userId, formId, key);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        //转化
        return ResultVo.success(FillRegisteryUtil.toFillRegistryReq(fillRegistry));
    }

    /**
     * 问卷表 获取答案
     *
     * @param fId     表id
     * @param request HttpServletRequest
     * @return 成功+数据（数据可为空
     */
    @GetMapping("/questionnaire/filled")
    public ResultVo getQuestionnaireAnswer(@RequestParam("formId") String fId, HttpServletRequest request) {
        //获取缓存key
        String key = CookieUtil.getKeyFromFormIdCookie(request,fId);

        //若缓存不存在
        if (key == null) return ResultVo.success();
        FillRegistry fillRegistry;
        try {
            //只查缓存
            fillRegistry = fillRegistryService.getAnswer(null, null, key);
        } catch (Exception e) {
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        return ResultVo.success(FillRegisteryUtil.toFillRegistryReq(fillRegistry));
    }


}
