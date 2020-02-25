package com.formtools.service.impl;

import com.formtools.enums.ErrorMsg;
import com.formtools.mapper.UserMapper;
import com.formtools.model.UserModel;
import com.formtools.service.UserService;
import com.formtools.utils.*;
import com.formtools.vo.ResultVo;
import com.formtools.utils.MyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
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
    //@Autowired
    private static ConcurrentHashMap<String,Examiner> emailCodeReservoir=new ConcurrentHashMap();

    @Resource
    private UserMapper userMapper;

    public UserModel getUser(Map<String, Object> map) {
        return userMapper.getUser(map);
    }

    public boolean addUser(UserModel userModel) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", userModel.getEmail());
        UserModel haduser = getUser(map);
        int n = 0;
        if (haduser == null && MyUtils.isUserFormat(userModel)) {
            n = userMapper.addUser(userModel);
        }
        if (n > 0) {
            return true;
        }
        return false;
    }

    public boolean updateUser(UserModel userModelMessage, UserModel userModel){
        String userId= userModelMessage.getUserId();
        int n=userMapper.updateUser(userModel);
        if(n>0)return true;
        return false;
    }

    @Transactional //开启事务控制
    public ResultVo sendEmailCode(String email) throws MessagingException {
        Map<String,Object> tempMap=new HashMap<>();
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
}
