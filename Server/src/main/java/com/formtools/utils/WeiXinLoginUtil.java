package com.formtools.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author myl
 * @create 2020-02-23  21:41
 */
public class WeiXinLoginUtil {
    private static Map<String, String> wxSceneMap;

    public static Map<String, String> getwxSceneMap() {
        if (wxSceneMap == null) {
            wxSceneMap = new HashMap();
        }
        return wxSceneMap;
    }
}
