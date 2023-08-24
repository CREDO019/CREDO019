package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzrz extends zzru {
    public static final Object zzd = new Object();
    private final Object zze;
    private final Object zzf;

    private zzrz(zzcn zzcnVar, Object obj, Object obj2) {
        super(zzcnVar);
        this.zze = obj;
        this.zzf = obj2;
    }

    public static zzrz zzq(zzbg zzbgVar) {
        return new zzrz(new zzsa(zzbgVar), zzcm.zza, zzd);
    }

    public static zzrz zzr(zzcn zzcnVar, Object obj, Object obj2) {
        return new zzrz(zzcnVar, obj, obj2);
    }

    @Override // com.google.android.gms.internal.ads.zzru, com.google.android.gms.internal.ads.zzcn
    public final int zza(Object obj) {
        Object obj2;
        zzcn zzcnVar = this.zzc;
        if (zzd.equals(obj) && (obj2 = this.zzf) != null) {
            obj = obj2;
        }
        return zzcnVar.zza(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzru, com.google.android.gms.internal.ads.zzcn
    public final zzck zzd(int r2, zzck zzckVar, boolean z) {
        this.zzc.zzd(r2, zzckVar, z);
        if (zzel.zzT(zzckVar.zzc, this.zzf) && z) {
            zzckVar.zzc = zzd;
        }
        return zzckVar;
    }

    @Override // com.google.android.gms.internal.ads.zzru, com.google.android.gms.internal.ads.zzcn
    public final zzcm zze(int r2, zzcm zzcmVar, long j) {
        this.zzc.zze(r2, zzcmVar, j);
        if (zzel.zzT(zzcmVar.zzc, this.zze)) {
            zzcmVar.zzc = zzcm.zza;
        }
        return zzcmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzru, com.google.android.gms.internal.ads.zzcn
    public final Object zzf(int r2) {
        Object zzf = this.zzc.zzf(r2);
        return zzel.zzT(zzf, this.zzf) ? zzd : zzf;
    }

    public final zzrz zzp(zzcn zzcnVar) {
        return new zzrz(zzcnVar, this.zze, this.zzf);
    }
}
