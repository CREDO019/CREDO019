package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzbn {
    public final Object zza;
    public final int zzb;
    public final int zzc;
    public final long zzd;
    public final int zze;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzbn(zzbn zzbnVar) {
        this.zza = zzbnVar.zza;
        this.zzb = zzbnVar.zzb;
        this.zzc = zzbnVar.zzc;
        this.zzd = zzbnVar.zzd;
        this.zze = zzbnVar.zze;
    }

    public zzbn(Object obj, int r9, int r10, long j) {
        this(obj, r9, r10, j, -1);
    }

    private zzbn(Object obj, int r2, int r3, long j, int r6) {
        this.zza = obj;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = j;
        this.zze = r6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzbn) {
            zzbn zzbnVar = (zzbn) obj;
            return this.zza.equals(zzbnVar.zza) && this.zzb == zzbnVar.zzb && this.zzc == zzbnVar.zzc && this.zzd == zzbnVar.zzd && this.zze == zzbnVar.zze;
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((this.zza.hashCode() + 527) * 31) + this.zzb) * 31) + this.zzc) * 31) + ((int) this.zzd)) * 31) + this.zze;
    }

    public final zzbn zza(Object obj) {
        return this.zza.equals(obj) ? this : new zzbn(obj, this.zzb, this.zzc, this.zzd, this.zze);
    }

    public final boolean zzb() {
        return this.zzb != -1;
    }

    public zzbn(Object obj, long j) {
        this(obj, -1, -1, j, -1);
    }

    public zzbn(Object obj, long j, int r11) {
        this(obj, -1, -1, j, r11);
    }
}
