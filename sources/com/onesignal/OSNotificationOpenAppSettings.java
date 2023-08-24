package com.onesignal;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OSNotificationOpenAppSettings.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, m183d2 = {"Lcom/onesignal/OSNotificationOpenAppSettings;", "", "()V", "getShouldOpenActivity", "", "context", "Landroid/content/Context;", "getSuppressLaunchURL", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public final class OSNotificationOpenAppSettings {
    public static final OSNotificationOpenAppSettings INSTANCE = new OSNotificationOpenAppSettings();

    private OSNotificationOpenAppSettings() {
    }

    public final boolean getShouldOpenActivity(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !Intrinsics.areEqual("DISABLE", OSUtils.getManifestMeta(context, "com.onesignal.NotificationOpened.DEFAULT"));
    }

    public final boolean getSuppressLaunchURL(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return OSUtils.getManifestMetaBoolean(context, "com.onesignal.suppressLaunchURLs");
    }
}
