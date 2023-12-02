package com.qx.learn.javaBase.CommonTools.MyTools;

import java.util.Map;

public final class MapUtility {

    private MapUtility() {}

    public static boolean any(Map<?, ?> map) {
        return map != null && map.size() > 0;
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return !any(map);
    }

}