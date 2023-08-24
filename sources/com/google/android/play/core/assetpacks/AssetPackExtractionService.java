package com.google.android.play.core.assetpacks;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.play.core.internal.C2494af;
import com.onesignal.OneSignalDbContract;

/* loaded from: classes3.dex */
public class AssetPackExtractionService extends Service {

    /* renamed from: a */
    Context f371a;

    /* renamed from: b */
    C2464j f372b;

    /* renamed from: c */
    C2382bb f373c;

    /* renamed from: d */
    private final C2494af f374d = new C2494af("AssetPackExtractionService");

    /* renamed from: e */
    private BinderC2380b f375e;

    /* renamed from: f */
    private NotificationManager f376f;

    /* renamed from: b */
    private final synchronized void m1041b(Bundle bundle) {
        String string = bundle.getString("notification_title");
        String string2 = bundle.getString("notification_subtext");
        long j = bundle.getLong("notification_timeout");
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("notification_on_click_intent");
        Notification.Builder timeoutAfter = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(this.f371a, "playcore-assetpacks-service-notification-channel").setTimeoutAfter(j) : new Notification.Builder(this.f371a).setPriority(-2);
        if (pendingIntent != null) {
            timeoutAfter.setContentIntent(pendingIntent);
        }
        timeoutAfter.setSmallIcon(17301633).setOngoing(false).setContentTitle(string).setSubText(string2);
        if (Build.VERSION.SDK_INT >= 21) {
            timeoutAfter.setColor(bundle.getInt("notification_color")).setVisibility(-1);
        }
        Notification build = timeoutAfter.build();
        this.f374d.m805c("Starting foreground service.", new Object[0]);
        this.f372b.m847a(true);
        if (Build.VERSION.SDK_INT >= 26) {
            this.f376f.createNotificationChannel(new NotificationChannel("playcore-assetpacks-service-notification-channel", bundle.getString("notification_channel_name"), 2));
        }
        startForeground(-1883842196, build);
    }

    /* renamed from: a */
    public final synchronized Bundle m1042a(Bundle bundle) {
        int r0 = bundle.getInt("action_type");
        C2494af c2494af = this.f374d;
        Integer valueOf = Integer.valueOf(r0);
        c2494af.m808a("updateServiceState: %d", valueOf);
        if (r0 == 1) {
            m1041b(bundle);
        } else if (r0 == 2) {
            m1043a();
        } else {
            this.f374d.m806b("Unknown action type received: %d", valueOf);
        }
        return new Bundle();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void m1043a() {
        this.f374d.m805c("Stopping service.", new Object[0]);
        this.f372b.m847a(false);
        stopForeground(true);
        stopSelf();
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f375e;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.f374d.m808a("onCreate", new Object[0]);
        C2436db.m907a(getApplicationContext()).mo955a(this);
        this.f375e = new BinderC2380b(this.f371a, this, this.f373c);
        this.f376f = (NotificationManager) this.f371a.getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME);
    }
}
