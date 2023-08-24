package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzkc {
    private final zzhi zza;

    @Deprecated
    public zzkc(Context context, zzclw zzclwVar, byte[] bArr) {
        this.zza = new zzhi(context, zzclwVar, null);
    }

    @Deprecated
    public final zzkc zza(final zzjf zzjfVar) {
        zzhi zzhiVar = this.zza;
        zzdd.zzf(!zzhiVar.zzl);
        zzhiVar.zzf = new zzfsv() { // from class: com.google.android.gms.internal.ads.zzha
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                return zzjf.this;
            }
        };
        return this;
    }

    @Deprecated
    public final zzkc zzb(final zzvw zzvwVar) {
        zzhi zzhiVar = this.zza;
        zzdd.zzf(!zzhiVar.zzl);
        zzhiVar.zze = new zzfsv() { // from class: com.google.android.gms.internal.ads.zzhb
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                return zzvw.this;
            }
        };
        return this;
    }

    @Deprecated
    public final zzkd zzc() {
        zzhi zzhiVar = this.zza;
        zzdd.zzf(!zzhiVar.zzl);
        zzhiVar.zzl = true;
        return new zzkd(zzhiVar);
    }
}
