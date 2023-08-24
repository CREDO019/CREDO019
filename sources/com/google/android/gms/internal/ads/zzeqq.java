package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeqq implements zzeun {
    private final zzfyy zza;
    private final Context zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeqq(zzfyy zzfyyVar, Context context) {
        this.zza = zzfyyVar;
        this.zzb = context;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 14;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeqp
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeqq.this.zzc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzeqr zzc() throws Exception {
        Intent registerReceiver;
        double d;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziy)).booleanValue() || Build.VERSION.SDK_INT < 33) {
            registerReceiver = this.zzb.registerReceiver(null, intentFilter);
        } else {
            registerReceiver = this.zzb.registerReceiver(null, intentFilter, 4);
        }
        boolean z = false;
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            d = registerReceiver.getIntExtra("level", -1) / registerReceiver.getIntExtra("scale", -1);
            if (intExtra == 2 || intExtra == 5) {
                z = true;
            }
        } else {
            d = -1.0d;
        }
        return new zzeqr(d, z);
    }
}
