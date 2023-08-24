package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public class zzkp {
    private static final zzjo zzb = zzjo.zza();
    protected volatile zzlj zza;
    private volatile zzjb zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzkp) {
            zzkp zzkpVar = (zzkp) obj;
            zzlj zzljVar = this.zza;
            zzlj zzljVar2 = zzkpVar.zza;
            if (zzljVar == null && zzljVar2 == null) {
                return zzb().equals(zzkpVar.zzb());
            }
            if (zzljVar == null || zzljVar2 == null) {
                if (zzljVar != null) {
                    zzkpVar.zzc(zzljVar.zzbR());
                    return zzljVar.equals(zzkpVar.zza);
                }
                zzc(zzljVar2.zzbR());
                return this.zza.equals(zzljVar2);
            }
            return zzljVar.equals(zzljVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zziy) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzbz();
        }
        return 0;
    }

    public final zzjb zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzjb.zzb;
            } else {
                this.zzc = this.zza.zzbv();
            }
            return this.zzc;
        }
    }

    protected final void zzc(zzlj zzljVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzljVar;
                    this.zzc = zzjb.zzb;
                } catch (zzkm unused) {
                    this.zza = zzljVar;
                    this.zzc = zzjb.zzb;
                }
            }
        }
    }
}
