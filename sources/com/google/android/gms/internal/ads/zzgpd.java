package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzgpd {
    private static final zzgnz zzb = zzgnz.zza();
    protected volatile zzgpx zza;
    private volatile zzgnf zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzgpd) {
            zzgpd zzgpdVar = (zzgpd) obj;
            zzgpx zzgpxVar = this.zza;
            zzgpx zzgpxVar2 = zzgpdVar.zza;
            if (zzgpxVar == null && zzgpxVar2 == null) {
                return zzb().equals(zzgpdVar.zzb());
            }
            if (zzgpxVar == null || zzgpxVar2 == null) {
                if (zzgpxVar != null) {
                    zzgpdVar.zzc(zzgpxVar.zzbh());
                    return zzgpxVar.equals(zzgpdVar.zza);
                }
                zzc(zzgpxVar2.zzbh());
                return this.zza.equals(zzgpxVar2);
            }
            return zzgpxVar.equals(zzgpxVar2);
        }
        return false;
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzgnb) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzax();
        }
        return 0;
    }

    public final zzgnf zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzgnf.zzb;
            } else {
                this.zzc = this.zza.zzas();
            }
            return this.zzc;
        }
    }

    protected final void zzc(zzgpx zzgpxVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzgpxVar;
                    this.zzc = zzgnf.zzb;
                } catch (zzgoz unused) {
                    this.zza = zzgpxVar;
                    this.zzc = zzgnf.zzb;
                }
            }
        }
    }
}
