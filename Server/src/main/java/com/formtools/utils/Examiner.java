package com.formtools.utils;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author NaNRailgun
 * 验证码剩余时间检测
 */
public class Examiner {

    private String code;
    private LocalDateTime time;


    /**
     * 返回剩余时间  剩余时间为0则返回空字符串
     * @param now 当前时间
     * @return 剩余时间 1-59s||”“
     */
    public String timeComputer(LocalDateTime now){
        Duration duration=Duration.between(time,now);
        if (duration.toMillis()<60*1000)
            return String.valueOf((60-(duration.toMillis()/1000)));
        return "";
    }


    public Examiner(String code, LocalDateTime time) {
        this.code = code;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
