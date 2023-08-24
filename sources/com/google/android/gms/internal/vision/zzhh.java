package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class zzhh {
    private static final zzgd zzrl = zzgd.zzfl();
    private zzfh zzxx;
    private volatile zzic zzxy;
    private volatile zzfh zzxz;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzhh) {
            zzhh zzhhVar = (zzhh) obj;
            zzic zzicVar = this.zzxy;
            zzic zzicVar2 = zzhhVar.zzxy;
            if (zzicVar == null && zzicVar2 == null) {
                return zzdk().equals(zzhhVar.zzdk());
            }
            if (zzicVar == null || zzicVar2 == null) {
                if (zzicVar != null) {
                    return zzicVar.equals(zzhhVar.zzh(zzicVar.zzgd()));
                }
                return zzh(zzicVar2.zzgd()).equals(zzicVar2);
            }
            return zzicVar.equals(zzicVar2);
        }
        return false;
    }

    private final zzic zzh(zzic zzicVar) {
        if (this.zzxy == null) {
            synchronized (this) {
                if (this.zzxy == null) {
                    try {
                        this.zzxy = zzicVar;
                        this.zzxz = zzfh.zzrx;
                    } catch (zzhc unused) {
                        this.zzxy = zzicVar;
                        this.zzxz = zzfh.zzrx;
                    }
                }
            }
        }
        return this.zzxy;
    }

    public final zzic zzi(zzic zzicVar) {
        zzic zzicVar2 = this.zzxy;
        this.zzxx = null;
        this.zzxz = null;
        this.zzxy = zzicVar;
        return zzicVar2;
    }

    public final int zzgf() {
        if (this.zzxz != null) {
            return this.zzxz.size();
        }
        if (this.zzxy != null) {
            return this.zzxy.zzgf();
        }
        return 0;
    }

    public final zzfh zzdk() {
        if (this.zzxz != null) {
            return this.zzxz;
        }
        synchronized (this) {
            if (this.zzxz != null) {
                return this.zzxz;
            }
            if (this.zzxy == null) {
                this.zzxz = zzfh.zzrx;
            } else {
                this.zzxz = this.zzxy.zzdk();
            }
            return this.zzxz;
        }
    }
}
