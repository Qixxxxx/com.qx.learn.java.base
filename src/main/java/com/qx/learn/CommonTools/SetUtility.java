package com.qx.learn.CommonTools;

import java.util.Set;

public class SetUtility {

    public SetUtility() {
    }

    public static boolean any(Set<?> set) {
        return set != null && set.size() > 0;
    }

    public static boolean isNullOrEmpty(Set<?> set) {
        return !any(set);
    }
}

