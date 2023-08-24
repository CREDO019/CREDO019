package com.onesignal;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IntentGeneratorForAttachingToNotifications.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\rJ\b\u0010\u0012\u001a\u00020\u000fH\u0003J\b\u0010\u0013\u001a\u00020\u000fH\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m183d2 = {"Lcom/onesignal/IntentGeneratorForAttachingToNotifications;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "notificationOpenedClassAndroid22AndOlder", "Ljava/lang/Class;", "notificationOpenedClassAndroid23Plus", "getNewActionPendingIntent", "Landroid/app/PendingIntent;", "requestCode", "", "oneSignalIntent", "Landroid/content/Intent;", "getNewBaseIntent", "notificationId", "getNewBaseIntentAndroidAPI22AndOlder", "getNewBaseIntentAndroidAPI23Plus", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public final class IntentGeneratorForAttachingToNotifications {
    private final Context context;
    private final Class<?> notificationOpenedClassAndroid22AndOlder;
    private final Class<?> notificationOpenedClassAndroid23Plus;

    public IntentGeneratorForAttachingToNotifications(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.notificationOpenedClassAndroid23Plus = NotificationOpenedReceiver.class;
        this.notificationOpenedClassAndroid22AndOlder = NotificationOpenedReceiverAndroid22AndOlder.class;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Intent getNewBaseIntent(int r3) {
        Intent newBaseIntentAndroidAPI22AndOlder;
        if (Build.VERSION.SDK_INT >= 23) {
            newBaseIntentAndroidAPI22AndOlder = getNewBaseIntentAndroidAPI23Plus();
        } else {
            newBaseIntentAndroidAPI22AndOlder = getNewBaseIntentAndroidAPI22AndOlder();
        }
        Intent addFlags = newBaseIntentAndroidAPI22AndOlder.putExtra(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, r3).addFlags(603979776);
        Intrinsics.checkNotNullExpressionValue(addFlags, "intent\n            .putE…Y_CLEAR_TOP\n            )");
        return addFlags;
    }

    private final Intent getNewBaseIntentAndroidAPI23Plus() {
        return new Intent(this.context, this.notificationOpenedClassAndroid23Plus);
    }

    @Deprecated(message = "Use getNewBaseIntentAndroidAPI23Plus instead for Android 6+")
    private final Intent getNewBaseIntentAndroidAPI22AndOlder() {
        Intent intent = new Intent(this.context, this.notificationOpenedClassAndroid22AndOlder);
        intent.addFlags(403177472);
        return intent;
    }

    public final PendingIntent getNewActionPendingIntent(int r3, Intent oneSignalIntent) {
        Intrinsics.checkNotNullParameter(oneSignalIntent, "oneSignalIntent");
        return PendingIntent.getActivity(this.context, r3, oneSignalIntent, 201326592);
    }
}
