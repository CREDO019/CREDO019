package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzhi {
    final Context zza;
    final zzde zzb;
    final zzfsv zzc;
    final zzfsv zzd;
    zzfsv zze;
    zzfsv zzf;
    final zzfsv zzg;
    final zzfru zzh;
    final Looper zzi;
    final zzk zzj;
    final zzkb zzk;
    boolean zzl;
    final zzgt zzm;

    public zzhi(final Context context, zzclw zzclwVar, byte[] bArr) {
        zzhc zzhcVar = new zzhc(zzclwVar, null);
        zzhd zzhdVar = new zzhd(context);
        zzfsv zzfsvVar = new zzfsv() { // from class: com.google.android.gms.internal.ads.zzhe
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                return new zzvo(context);
            }
        };
        zzhf zzhfVar = new zzfsv() { // from class: com.google.android.gms.internal.ads.zzhf
            @Override // com.google.android.gms.internal.ads.zzfsv
            public final Object zza() {
                return new zzgu();
            }
        };
        zzhg zzhgVar = new zzhg(context);
        zzhh zzhhVar = new zzfru() { // from class: com.google.android.gms.internal.ads.zzhh
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                return new zzmq((zzde) obj);
            }
        };
        this.zza = context;
        this.zzc = zzhcVar;
        this.zzd = zzhdVar;
        this.zze = zzfsvVar;
        this.zzf = zzhfVar;
        this.zzg = zzhgVar;
        this.zzh = zzhhVar;
        this.zzi = zzel.zzE();
        this.zzj = zzk.zza;
        this.zzk = zzkb.zze;
        this.zzm = new zzgt(0.97f, 1.03f, 1000L, 1.0E-7f, zzel.zzv(20L), zzel.zzv(500L), 0.999f, null);
        this.zzb = zzde.zza;
    }

    public static /* synthetic */ zzsf zza(Context context) {
        return new zzrt(context, new zzza());
    }
}
