package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqt implements Callable {
    private final zzaqb zza;
    private final zzamh zzb;

    public zzaqt(zzaqb zzaqbVar, zzamh zzamhVar) {
        this.zza = zzaqbVar;
        this.zzb = zzamhVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        if (this.zza.zzl() != null) {
            this.zza.zzl().get();
        }
        zzamx zzc = this.zza.zzc();
        if (zzc != null) {
            try {
                synchronized (this.zzb) {
                    zzamh zzamhVar = this.zzb;
                    byte[] zzaw = zzc.zzaw();
                    zzamhVar.zzak(zzaw, 0, zzaw.length, zzgnz.zza());
                }
                return null;
            } catch (zzgoz | NullPointerException unused) {
                return null;
            }
        }
        return null;
    }
}
