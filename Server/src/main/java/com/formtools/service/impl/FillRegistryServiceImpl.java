package com.formtools.service.impl;

import com.formtools.mapper.BuiltFormMapper;
import com.formtools.mapper.FillRegistryMapper;
import com.formtools.model.BuiltForm;
import com.formtools.model.FillRegistry;
import com.formtools.service.FillRegistryService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class FillRegistryServiceImpl implements FillRegistryService {

    @Resource
    private BuiltFormMapper builtFormMapper;

    @Resource
    private FillRegistryMapper fillRegistryMapper;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 获取表单基本信息、题目...
     *
     * @param formId 表单id
     * @return 表单基本信息（标题，题目，建表人...
     */
    @Override
    public BuiltForm getFormInfo(Long formId) {

        String FormInfoKey = formId.toString();

        //从缓存获取表单 题目信息
        BuiltForm builtForm = (BuiltForm) redisTemplate.opsForValue().get(FormInfoKey);
        //若缓存中不存在该表单 题目信息
        if (builtForm == null) {
            synchronized (this) {
                builtForm = builtFormMapper.getBuiltForm(formId);
                //若数据库查无此表 返回空
                if (builtForm == null) return null;
                redisTemplate.opsForValue().set(FormInfoKey, builtForm);
            }
        }
        return builtForm;
    }

    /**
     * 实时保存答案
     *
     * @param key          缓存的key 可为空
     * @param fillRegistry 答案 填表人信息
     * @return 缓存的key
     */
    public String currentSaveAnswer(String key, FillRegistry fillRegistry) {
        //作为缓存的key
        String uuid = key;
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        //设置过期时间为7天
        redisTemplate.opsForValue().set(uuid, fillRegistry, 7, TimeUnit.DAYS);
        return uuid;
    }

    /**
     * 获取答案
     *
     * @param userId 用户id
     * @param formId 表单id
     * @param key    缓存的key 可为空
     * @return 答案
     */
    public FillRegistry getAnswer(Long userId, Long formId, String key) {
        //若缓存不存在 即为改表或填表 直接获取数据库
        if (key == null || "".equals(key)) {
            return fillRegistryMapper.getFilledRegistry(userId, formId);
        } else {
            return (FillRegistry) redisTemplate.opsForValue().get(key);
        }
    }

    public boolean insertRegistry(FillRegistry fillRegistry, String key) {
        if (fillRegistryMapper.insertFilledRegistry(fillRegistry) > 0) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }
}
