package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;
import com.onesignal.OneSignalDbContract;

/* loaded from: classes.dex */
public class NotificationTarget extends SimpleTarget<Bitmap> {
    private final Context context;
    private final Notification notification;
    private final int notificationId;
    private final String notificationTag;
    private final RemoteViews remoteViews;
    private final int viewId;

    @Override // com.bumptech.glide.request.target.Target
    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
    }

    public NotificationTarget(Context context, int r9, RemoteViews remoteViews, Notification notification, int r12) {
        this(context, r9, remoteViews, notification, r12, null);
    }

    public NotificationTarget(Context context, int r11, RemoteViews remoteViews, Notification notification, int r14, String str) {
        this(context, Integer.MIN_VALUE, Integer.MIN_VALUE, r11, remoteViews, notification, r14, str);
    }

    public NotificationTarget(Context context, int r2, int r3, int r4, RemoteViews remoteViews, Notification notification, int r7, String str) {
        super(r2, r3);
        this.context = (Context) Preconditions.checkNotNull(context, "Context must not be null!");
        this.notification = (Notification) Preconditions.checkNotNull(notification, "Notification object can not be null!");
        this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews, "RemoteViews object can not be null!");
        this.viewId = r4;
        this.notificationId = r7;
        this.notificationTag = str;
    }

    private void update() {
        ((NotificationManager) Preconditions.checkNotNull((NotificationManager) this.context.getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME))).notify(this.notificationTag, this.notificationId, this.notification);
    }

    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }
}
