package com.qx.learn.CommonTools;

import java.util.Map;

public final class MapUtility {
    public MapUtility() {
        throw new IllegalStateException();
    }

    public static boolean any(Map<?, ?> map) {
        return map != null && map.size() > 0;
    }

    public static boolean isNullOrEmpty(Map<?, ?> map) {
        return !any(map);
    }

}