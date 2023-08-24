package com.google.android.play.core.appupdate;

/* loaded from: classes3.dex */
public abstract class AppUpdateOptions {

    /* loaded from: classes3.dex */
    public static abstract class Builder {
        public abstract AppUpdateOptions build();

        public abstract Builder setAllowAssetPackDeletion(boolean z);

        public abstract Builder setAppUpdateType(int r1);
    }

    public static AppUpdateOptions defaultOptions(int r0) {
        return newBuilder(r0).build();
    }

    public static Builder newBuilder(int r1) {
        C2348u c2348u = new C2348u();
        c2348u.setAppUpdateType(r1);
        c2348u.setAllowAssetPackDeletion(false);
        return c2348u;
    }

    public abstract boolean allowAssetPackDeletion();

    public abstract int appUpdateType();
}
