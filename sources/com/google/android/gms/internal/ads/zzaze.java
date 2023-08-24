package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaze {
    public final zzayp zza;
    public final zzazb zzb;
    public final Object zzc;
    public final zzasz[] zzd;

    public zzaze(zzayp zzaypVar, zzazb zzazbVar, Object obj, zzasz[] zzaszVarArr) {
        this.zza = zzaypVar;
        this.zzb = zzazbVar;
        this.zzc = obj;
        this.zzd = zzaszVarArr;
    }

    public final boolean zza(zzaze zzazeVar, int r5) {
        return zzazeVar != null && zzban.zzo(this.zzb.zza(r5), zzazeVar.zzb.zza(r5)) && zzban.zzo(this.zzd[r5], zzazeVar.zzd[r5]);
    }
}
