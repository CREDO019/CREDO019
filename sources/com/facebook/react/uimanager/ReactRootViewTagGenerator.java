package com.facebook.react.uimanager;

/* loaded from: classes.dex */
public class ReactRootViewTagGenerator {
    private static final int ROOT_VIEW_TAG_INCREMENT = 10;
    private static int sNextRootViewTag = 1;

    public static synchronized int getNextRootViewTag() {
        int r1;
        synchronized (ReactRootViewTagGenerator.class) {
            r1 = sNextRootViewTag;
            sNextRootViewTag = r1 + 10;
        }
        return r1;
    }
}
