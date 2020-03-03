package com.formtools.service.impl;

import com.formtools.Exception.ParamException;
import com.formtools.enums.ErrorMsg;
import com.formtools.mapper.UserMapper;
import com.formtools.model.UserModel;
import com.formtools.service.UserService;
import com.formtools.utils.*;
import com.formtools.vo.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author myl
 * @create 2020-02-05  23:22
 */
@Service
public class UserServiceImpl implements UserService {

    //email验证码储存器
    private static ConcurrentHashMap<String,Examiner> emailCodeReservoir=new ConcurrentHashMap();

    @Resource
    private UserMapper userMapper;

    public UserModel getUser(Map<String,String> map) {
        return userMapper.getUser(map);
    }

    public boolean addUser(UserModel userModel) {
        long timeMillis = System.currentTimeMillis();
        String userId = "user" + timeMillis;
        userModel.setProfile("");
        userModel.setUserId(userId);
        Map<String, String> map = new HashMap();
        map.put("email", userModel.getEmail());
        UserModel haduser = getUser(map);
        int n = 0;
        if (haduser == null) {
            n = userMapper.addUser(userModel);
        }
        return n > 0;
    }

    public boolean updateUser(UserModel userModelMessage, UserModel userModel){
        String userId= userModelMessage.getUserId();
        int n=userMapper.updateUser(userModel);
        return n > 0;
    }

    @Transactional //开启事务控制
    public ResultVo sendEmailCode(String email) throws MessagingException {
        Map<String,String> tempMap=new HashMap<>();
        tempMap.put("email",email);
        UserModel realUser=userMapper.getUser(tempMap);
        //若账号已存在
        if (realUser!=null)
            return ResultVo.fail(ErrorMsg.ACCOUNT_EXIT);
        Examiner examiner=emailCodeReservoir.get(email);
        //验证信息已存在缓存
        if (examiner!=null){
            String remainTime=examiner.timeComputer(LocalDateTime.now());
            //若未超过60s
            if (!remainTime.equals(""))
                return ResultVo.fail(ErrorMsg.OPERAT_FREQUENCY,remainTime);
        }

        String code= CodeUtil.createCode();
        //发送邮件
        EmailUtil.SendEmail(email,code);
        //置入缓存
        emailCodeReservoir.put(email,new Examiner(code,LocalDateTime.now()));
        return ResultVo.success();
    }

    /**
     * 判断验证码是否正确
     * @param userModel 注册用户信息（只需用户email）
     * @param code 验证码
     * @return true
     * 验证码错误抛参数错误异常
     */
    public boolean isTrueCode(UserModel userModel,String code){
        if (code!=null && !code.equals("")){
            Examiner examiner=emailCodeReservoir.get(userModel.getEmail());
            if (examiner!=null && examiner.getCode().equals(code)){
                emailCodeReservoir.remove(userModel.getEmail());
                return true;
            }
        }
        Map<String,String> map=new HashMap<>();
        map.put("code","验证码错误");
        throw new ParamException(map);
    }

    /**
     * 注册用户
     * @param userModel 注册用户信息
     * @param code 验证码
     * @return 是否成功
     */
    public boolean register(UserModel userModel,String code){

        if (isTrueCode(userModel,code)) return addUser(userModel);
        return false;
    }

    /**
     * 保存用户上传的头像（头像文件名以用户id命名）
     * @param multipartFile 头像文件
     * @param id 用户id
     * @return 图片储存地址 上传成功
     * 失败抛异常
     */
    public String keepImage(MultipartFile multipartFile, String id) throws IOException {
        //在开发测试模式时，得到地址为：{项目跟目录}/target/static/images/users/images/id.jpg
        File upload=new File(ResourceUtils.getURL("classpath:").getPath(),"static\\images\\users\\images\\"+id+".jpg");
        if(!upload.exists()){
            upload.mkdirs();
        }
        multipartFile.transferTo(upload);
        return upload.getName();
    }

    /**
     * 用户邮箱登录验证
     * @param email 邮箱
     * @param password 密码
     * @return 成功返回用户id
     * 否则返回“”（空字符）
     */
    public String emailLogin(String email,String password){
        Map<String,String> map=new HashMap<>();
        map.put("email",email);
        UserModel userModel=userMapper.getUser(map);
        if (userModel!=null && userModel.getPassword().equals(password)){
            return userModel.getUserId();
        }
        return "";
    }
}
