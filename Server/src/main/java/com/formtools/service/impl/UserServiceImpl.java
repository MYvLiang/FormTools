package com.formtools.service.impl;

import com.formtools.Exception.ParamException;
import com.formtools.enums.ErrorMsg;
import com.formtools.mapper.UserMapper;
import com.formtools.model.EmailVerify;
import com.formtools.model.UserInfo;
import com.formtools.model.UserModel;
import com.formtools.model.UserVerify;
import com.formtools.service.UserService;
import com.formtools.utils.CodeUtil;
import com.formtools.utils.EmailUtil;
import com.formtools.utils.Examiner;
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

    //注册email验证码储存器
    private static ConcurrentHashMap<String, Examiner> emailCodeReservoir=new ConcurrentHashMap();

    //修改密码
    private static ConcurrentHashMap<String, Examiner> emailCodeReservoirResetPassword=new ConcurrentHashMap();

    @Resource
    private UserMapper userMapper;

    /**
     * 获取用户个人信息
     * @param userId
     * @return
     */
    public UserInfo getUserInfo(Long userId) {
        return userMapper.getUserInfo(userId);
    }

    /**
     * 添加邮箱用户
     * @param userModel
     * @return
     */
    @Transactional //事务控制
    public void addUser(UserModel userModel) {
        //获取userId （可优化
        long userId = System.currentTimeMillis();

        UserVerify userVerify=new UserVerify();
        userVerify.setUserId(userId);
        userVerify.setOpenid(userModel.getEmail());
        userVerify.setType('E');

        UserInfo userInfo=new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setNickname(userModel.getNickname());
        userInfo.setProfile("");

        EmailVerify emailVerify=new EmailVerify();
        emailVerify.setEmail(userModel.getEmail());
        emailVerify.setPassword(userModel.getPassword());

        userMapper.addUserVerify(userVerify);
        userMapper.addUserInfo(userInfo);
        userMapper.addEmailVerify(emailVerify);
    }

    /**
     * 修改用户个人信息
     * @param userModel
     * @return
     */
    public boolean updateUserInfo(UserModel userModel){
        UserInfo userInfo=new UserInfo();
        userInfo.setProfile(userModel.getProfile());
        userInfo.setUserId(userModel.getUserId());
        userInfo.setNickname(userModel.getNickname());

        return userMapper.updateUserInfo(userInfo) > 0;
    }

    /**
     * 发送邮件
     * @param email 待发送邮箱
     * @param type 类型 注册Z 修改密码X
     * @return
     * @throws MessagingException
     */
    @Transactional //开启事务控制
    public ResultVo sendEmailCode(String email,String type) throws MessagingException {

        Map<String,Examiner> map;
        UserVerify userVerify=userMapper.getUserVerify(email);

        if ("Z".equals(type)) {
            map=emailCodeReservoir;
            //若该验证方式已存在
            if (userVerify!=null) return ResultVo.fail(ErrorMsg.ACCOUNT_EXIT);
        }
        else if ("X".equals(type)){
            map=emailCodeReservoirResetPassword;
            if (userVerify==null) return ResultVo.fail(ErrorMsg.ACCOUNT_NOT_EXIT);
        }
        else return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);

        //获取缓存
        Examiner examiner=map.get(email);
        //若该缓存存在
        if (examiner!=null){
            String remainTime=examiner.timeComputer(LocalDateTime.now());
            //若未超过60s 返回剩余时间
            if (!remainTime.equals(""))
                return ResultVo.fail(ErrorMsg.OPERAT_FREQUENCY,remainTime);
        }
        String code= CodeUtil.createCode();
        //发送邮件
        EmailUtil.SendEmail(email,code);
        //置入缓存
        map.put(email,new Examiner(code,LocalDateTime.now()));
        return ResultVo.success();
    }

    /**
     * 判断验证码是否正确
     * @param userModel
     * @param code 验证码
     * @return true
     * 验证码错误抛参数错误异常
     */
    public boolean isTrueCode(UserModel userModel,String code,String type){
        Map<String,Examiner> map;
        if ("Z".equals(type)) {
            map=emailCodeReservoir;
        }
        else if ("X".equals(type)){
            map=emailCodeReservoirResetPassword;
        }
        else return false;
        //校验验证码 code
        if (code!=null && !code.equals("")){
            Examiner examiner=map.get(userModel.getEmail());
            if (examiner!=null && examiner.getCode().equals(code)){
                map.remove(userModel.getEmail());
                return true;
            }
        }
        Map<String,String> temp=new HashMap<>();
        temp.put("code","验证码错误");
        throw new ParamException(temp);
    }

    /**
     * 注册用户
     * @param userModel 注册用户信息
     * @param code 验证码
     * @return 是否成功
     */
    public boolean register(UserModel userModel,String code){

        if (isTrueCode(userModel,code,"Z")){
            try {
                //抓取异常 事务回滚
                addUser(userModel);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public boolean resetPassword(UserModel userModel,String code){
        if (isTrueCode(userModel,code,"X")){

            EmailVerify emailVerify=new EmailVerify();
            emailVerify.setEmail(userModel.getEmail());
            emailVerify.setPassword(userModel.getPassword());

            try {
                updateEmailVerify(emailVerify);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 重设密码
     * @param emailVerify
     */
    public void updateEmailVerify(EmailVerify emailVerify){
        userMapper.updateEmailVerify(emailVerify);
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
     * 否则返回0
     */
    public Long emailLogin(String email,String password){
        //查看 邮箱密码验证表 email_verify 中是否含有该账户
        EmailVerify emailVerify = userMapper.getEmailVerify(email);
        if (emailVerify!=null){
            if (emailVerify.getPassword().equals(password)){
                Long userId = userMapper.getUserVerify(email).getUserId();
                return userId;
            }
        }
        return 0L;
    }
}
