package com.facebook.react.uimanager.common;

/* loaded from: classes.dex */
public class ViewUtil {
    public static int getUIManagerType(int r1) {
        return r1 % 2 == 0 ? 2 : 1;
    }

    @Deprecated
    public static boolean isRootTag(int r1) {
        return r1 % 10 == 1;
    }
}
