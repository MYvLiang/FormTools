package com.formtools.controller;

import com.formtools.enums.ErrorMsg;
import com.formtools.model.FillQuestionnaire;
import com.formtools.model.FillRegistry;
import com.formtools.service.FillQuestionnaireService;
import com.formtools.utils.CookieUtil;
import com.formtools.utils.ValidationUtil;
import com.formtools.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

@Api(tags = "调查问卷")
@RestController
public class FillQuestionnaireController {

    @Resource
    private FillQuestionnaireService fillQuestionnaireService;

    @Resource
    private ValidationUtil validationUtil;

    /**
     * 提交问卷表答案
     * @param fillQuestionnaire 答案
     * @param request request
     * @param response response
     * @return 成功
     */
    @ApiOperation("提交问卷答案")
    @PostMapping("/fill-questionnaire")
    public ResultVo InsertFillQuestionnaire(@RequestBody FillQuestionnaire fillQuestionnaire, HttpServletRequest request, HttpServletResponse response){
        //参数验证
        validationUtil.validateParam(fillQuestionnaire,new Class[]{Default.class});
        //表单id
        String formId=fillQuestionnaire.getFormId().toString();
        //获取缓存key
        String key= CookieUtil.getKeyFromFormIdCookie(request,fillQuestionnaire.getFormId().toString());
        //若key不存在 1.已提交过一次 2.没实时保存就提交
        if (key==null){
            //返回重复提交失败
            return ResultVo.fail(ErrorMsg.REPEAT_COMMIT_ERROR);
        }

        boolean isSucceed;
        try {
            isSucceed=fillQuestionnaireService.insertFillQuestionnaire(key,fillQuestionnaire);
        } catch (Exception e) {
            //返回系统错误
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        if (isSucceed){
            Cookie cookie=new Cookie(formId,null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return ResultVo.success();
        }
        //返回提交失败
        return ResultVo.fail(ErrorMsg.COMMIT_FAIL_ERROR);
    }

    /**
     * 获取问卷答案缓存
     * @param fid 表id
     * @param request request
     * @return 问卷表 答案缓存
     */
    @ApiOperation("获取问卷答案")
    @GetMapping("/fill-questionnaire")
    public ResultVo getFillQuestionnaire(@RequestParam("formId") String fid,HttpServletRequest request){
        //获取缓存key
        String key=CookieUtil.getKeyFromFormIdCookie(request,fid);
        if (key!=null){
            FillRegistry fillQuestionnaire=fillQuestionnaireService.getFillQuestionnaireFromCache(key);
            return ResultVo.success(fillQuestionnaire);
        }
        //key为空 返回成功 空
        return ResultVo.success();
    }
}
