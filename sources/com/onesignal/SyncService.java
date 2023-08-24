package com.onesignal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.onesignal.OSSyncService;

/* loaded from: classes3.dex */
public class SyncService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int r2, int r3) {
        OSSyncService.getInstance().doBackgroundSync(this, new OSSyncService.LegacySyncRunnable(this));
        return 1;
    }
}
