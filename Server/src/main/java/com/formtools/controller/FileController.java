package com.formtools.controller;

import com.formtools.enums.ErrorMsg;
import com.formtools.model.UserInfo;
import com.formtools.service.UserService;
import com.formtools.service.impl.FileServiceImpl;
import com.formtools.vo.ResultVo;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
public class FileController {

    @Resource
    private UserService userService;

    @Resource
    private FileServiceImpl fileService;


    @PostMapping("/file")
    public ResultVo uploadFile(@CookieValue("userId")
                               @NotNull(message = "登录异常 请重新登录")
                               @NotEmpty(message = "登录异常 请重新登录") String uid,
                               @RequestParam("formId") String formId, @RequestParam("file") MultipartFile multipartFile) {
        Long userId;
        try {
            userId = Long.parseLong(uid);
        } catch (NumberFormatException e) {
            return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
        }
        UserInfo userInfo = userService.getUserInfo(userId);
        String userName = userInfo.getNickname();//获取用户名

        try {
            if (fileService.uploadFile(uid, userName, formId, multipartFile)) {
                //将文件名返回
                return ResultVo.success(multipartFile.getOriginalFilename());
            }
        } catch (IOException e) {
            return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
        }
        return ResultVo.fail(ErrorMsg.FILE_UPLOAD_ERROR);
    }
}
