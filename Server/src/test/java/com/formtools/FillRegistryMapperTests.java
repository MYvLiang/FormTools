package com.formtools;

import com.alibaba.fastjson.JSONArray;
import com.formtools.mapper.FillRegistryMapper;
import com.formtools.model.FillRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author myl
 * @create 2020-04-04  11:13
 */
@SpringBootTest
class FillRegistryMapperTests {

    @Resource
    FillRegistryMapper fillRegistryMapper;

    @Test
    void testAdd(){
        JSONArray fileArray=new JSONArray();
        fileArray.add("www/a/file1.zip");
        fileArray.add("www/a/file2.zip");
        fileArray.add("www/a/file3.zip");
        FillRegistry fillRegistry=new FillRegistry(1114L,1234L,"contnet",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),fileArray.toString(),'T');
        int n= fillRegistryMapper.addRegistry(fillRegistry);
        System.out.println(n);
    }

    @Test
    void getFilledRegistry(){
        FillRegistry fillRegistry=fillRegistryMapper.getFilledRegistry(1111L,123L);
        System.out.println(fillRegistry);
    }

    @Test
    void testFindAllFilled(){
        List<FillRegistry> allFilled=fillRegistryMapper.findAllFilled(1234L);
        for(FillRegistry data:allFilled)
            System.out.println(data);
    }

    @Test
    void testupdateFilledRegistry(){
        JSONArray fileArray=new JSONArray();
        fileArray.add("www/a/file1.zip");
        fileArray.add("www/a/file2.zip");
        FillRegistry fillRegistry=new FillRegistry(1114L,1234L,"contnet2",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),fileArray.toString(),'F');
        int n= fillRegistryMapper.updateFilledRegistry(fillRegistry);
        System.out.println(n);
        fillRegistry=fillRegistryMapper.getFilledRegistry(1114L,1234L);
        System.out.println(fillRegistry);
    }

    @Test
    void testcancelFilledRegistry(){
        int n=fillRegistryMapper.cancelFilledRegistry(1114L,1234L,new Timestamp(System.currentTimeMillis()));
        System.out.println(n);
    }

    @Test
    void testdeleteFilledRegistry(){
        int n=fillRegistryMapper.deleteFilledRegistry(1113L,1234L);
    }
}
