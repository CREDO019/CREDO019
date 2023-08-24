package com.google.android.gms.common.api.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
final class zzc implements Runnable {
    final /* synthetic */ LifecycleCallback zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzd zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(zzd zzdVar, LifecycleCallback lifecycleCallback, String str) {
        this.zzc = zzdVar;
        this.zza = lifecycleCallback;
        this.zzb = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int r1;
        int r0;
        int r02;
        int r03;
        int r04;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3;
        zzd zzdVar = this.zzc;
        r1 = zzdVar.zzc;
        if (r1 > 0) {
            LifecycleCallback lifecycleCallback = this.zza;
            bundle = zzdVar.zzd;
            if (bundle != null) {
                bundle3 = zzdVar.zzd;
                bundle2 = bundle3.getBundle(this.zzb);
            } else {
                bundle2 = null;
            }
            lifecycleCallback.onCreate(bundle2);
        }
        r0 = this.zzc.zzc;
        if (r0 >= 2) {
            this.zza.onStart();
        }
        r02 = this.zzc.zzc;
        if (r02 >= 3) {
            this.zza.onResume();
        }
        r03 = this.zzc.zzc;
        if (r03 >= 4) {
            this.zza.onStop();
        }
        r04 = this.zzc.zzc;
        if (r04 >= 5) {
            this.zza.onDestroy();
        }
    }
}
