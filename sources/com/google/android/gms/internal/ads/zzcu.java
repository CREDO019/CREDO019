package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.HashSet;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzcu {
    public static final zzcu zza;
    @Deprecated
    public static final zzcu zzb;
    @Deprecated
    public static final zzn zzc;
    public final boolean zzA;
    public final zzfuy zzB;
    public final zzfva zzC;
    public final int zzd = Integer.MAX_VALUE;
    public final int zze = Integer.MAX_VALUE;
    public final int zzf = Integer.MAX_VALUE;
    public final int zzg = Integer.MAX_VALUE;
    public final int zzh = 0;
    public final int zzi = 0;
    public final int zzj = 0;
    public final int zzk = 0;
    public final int zzl;
    public final int zzm;
    public final boolean zzn;
    public final zzfuv zzo;
    public final int zzp;
    public final zzfuv zzq;
    public final int zzr;
    public final int zzs;
    public final int zzt;
    public final zzfuv zzu;
    public final zzfuv zzv;
    public final int zzw;
    public final int zzx;
    public final boolean zzy;
    public final boolean zzz;

    static {
        zzcu zzcuVar = new zzcu(new zzct());
        zza = zzcuVar;
        zzb = zzcuVar;
        zzc = new zzn() { // from class: com.google.android.gms.internal.ads.zzcs
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public zzcu(zzct zzctVar) {
        int r2;
        int r22;
        boolean z;
        zzfuv zzfuvVar;
        zzfuv zzfuvVar2;
        zzfuv zzfuvVar3;
        zzfuv zzfuvVar4;
        int r0;
        HashMap hashMap;
        HashSet hashSet;
        r2 = zzctVar.zze;
        this.zzl = r2;
        r22 = zzctVar.zzf;
        this.zzm = r22;
        z = zzctVar.zzg;
        this.zzn = z;
        zzfuvVar = zzctVar.zzh;
        this.zzo = zzfuvVar;
        this.zzp = 0;
        zzfuvVar2 = zzctVar.zzi;
        this.zzq = zzfuvVar2;
        this.zzr = 0;
        this.zzs = Integer.MAX_VALUE;
        this.zzt = Integer.MAX_VALUE;
        zzfuvVar3 = zzctVar.zzl;
        this.zzu = zzfuvVar3;
        zzfuvVar4 = zzctVar.zzm;
        this.zzv = zzfuvVar4;
        r0 = zzctVar.zzn;
        this.zzw = r0;
        this.zzx = 0;
        this.zzy = false;
        this.zzz = false;
        this.zzA = false;
        hashMap = zzctVar.zzo;
        this.zzB = zzfuy.zzc(hashMap);
        hashSet = zzctVar.zzp;
        this.zzC = zzfva.zzl(hashSet);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzcu zzcuVar = (zzcu) obj;
            if (this.zzn == zzcuVar.zzn && this.zzl == zzcuVar.zzl && this.zzm == zzcuVar.zzm && this.zzo.equals(zzcuVar.zzo) && this.zzq.equals(zzcuVar.zzq) && this.zzu.equals(zzcuVar.zzu) && this.zzv.equals(zzcuVar.zzv) && this.zzw == zzcuVar.zzw && this.zzB.equals(zzcuVar.zzB) && this.zzC.equals(zzcuVar.zzC)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.zzn ? 1 : 0) - 1048002209) * 31) + this.zzl) * 31) + this.zzm) * 31) + this.zzo.hashCode()) * 961) + this.zzq.hashCode()) * 961) + Integer.MAX_VALUE) * 31) + Integer.MAX_VALUE) * 31) + this.zzu.hashCode()) * 31) + this.zzv.hashCode()) * 31) + this.zzw) * 28629151) + this.zzB.hashCode()) * 31) + this.zzC.hashCode();
    }
}
