package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzjp implements zzjl {
    public final zzsb zza;
    public int zzd;
    public boolean zze;
    public final List zzc = new ArrayList();
    public final Object zzb = new Object();

    public zzjp(zzsi zzsiVar, boolean z) {
        this.zza = new zzsb(zzsiVar, z);
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final zzcn zza() {
        return this.zza.zzA();
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final Object zzb() {
        return this.zzb;
    }

    public final void zzc(int r1) {
        this.zzd = r1;
        this.zze = false;
        this.zzc.clear();
    }
}
