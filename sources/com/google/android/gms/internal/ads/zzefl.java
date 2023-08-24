package com.google.android.gms.internal.ads;

import android.database.sqlite.SQLiteDatabase;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzefl implements zzfhq {
    private final zzeez zza;
    private final zzefd zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzefl(zzeez zzeezVar, zzefd zzefdVar) {
        this.zza = zzeezVar;
        this.zzb = zzefdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbF(zzfhj zzfhjVar, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzbG(zzfhj zzfhjVar, String str, Throwable th) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfj)).booleanValue() && zzfhj.RENDERER == zzfhjVar && this.zza.zzc() != 0) {
            this.zza.zzf(com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime() - this.zza.zzc());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzc(zzfhj zzfhjVar, String str) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfj)).booleanValue()) {
            if (zzfhj.RENDERER == zzfhjVar) {
                this.zza.zzg(com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime());
            } else if (zzfhj.PRELOADED_LOADER == zzfhjVar || zzfhj.SERVER_TRANSACTION == zzfhjVar) {
                this.zza.zzh(com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime());
                final zzefd zzefdVar = this.zzb;
                final long zzd = this.zza.zzd();
                zzefdVar.zza.zza(new zzfgs() { // from class: com.google.android.gms.internal.ads.zzefc
                    @Override // com.google.android.gms.internal.ads.zzfgs
                    public final Object zza(Object obj) {
                        zzefd zzefdVar2 = zzefd.this;
                        long j = zzd;
                        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
                        if (zzefdVar2.zzf()) {
                            return null;
                        }
                        zzbgt zzg = zzbgu.zzg();
                        zzg.zzh(j);
                        byte[] zzaw = ((zzbgu) zzg.zzal()).zzaw();
                        zzefk.zzg(sQLiteDatabase, false, false);
                        zzefk.zzd(sQLiteDatabase, j, zzaw);
                        return null;
                    }
                });
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfhq
    public final void zzd(zzfhj zzfhjVar, String str) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfj)).booleanValue() && zzfhj.RENDERER == zzfhjVar && this.zza.zzc() != 0) {
            this.zza.zzf(com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime() - this.zza.zzc());
        }
    }
}
