package com.formtools.utils;

/**
 * @author myl
 * @create 2020-04-14  16:29
 */
public class IdBuilder {

    private static int formid=1;

    private static int userid=1;

    private static int sceneid=1;

    public static synchronized long getFormId(){
        formid=(formid+1)%1000;
        return System.currentTimeMillis()*1000+formid;
    }

    public static synchronized long getUserId(){
        userid=(userid+1)%1000;
        return System.currentTimeMillis()*1000+userid;
    }

    public static synchronized long getSceneId(){
        sceneid=(sceneid+1)%1000;
        return System.currentTimeMillis()*1000+sceneid;
    }
}
