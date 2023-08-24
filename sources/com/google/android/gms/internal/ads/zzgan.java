package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgan {
    private final zzgjq zza;

    private zzgan(zzgjq zzgjqVar) {
        this.zza = zzgjqVar;
    }

    public static zzgan zzd() {
        return new zzgan(zzgjt.zzd());
    }

    private final synchronized int zze() {
        int zza;
        zza = zzgep.zza();
        while (zzg(zza)) {
            zza = zzgep.zza();
        }
        return zza;
    }

    private final synchronized zzgjs zzf(zzgjl zzgjlVar) throws GeneralSecurityException {
        return zzh(zzgbe.zzc(zzgjlVar), zzgjlVar.zzi());
    }

    private final synchronized boolean zzg(int r3) {
        boolean z;
        Iterator it = this.zza.zzc().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (((zzgjs) it.next()).zza() == r3) {
                z = true;
                break;
            }
        }
        return z;
    }

    private final synchronized zzgjs zzh(zzgjg zzgjgVar, int r4) throws GeneralSecurityException {
        zzgjr zzd;
        int zze = zze();
        if (r4 == 2) {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        zzd = zzgjs.zzd();
        zzd.zza(zzgjgVar);
        zzd.zzb(zze);
        zzd.zzd(3);
        zzd.zzc(r4);
        return (zzgjs) zzd.zzal();
    }

    @Deprecated
    public final synchronized int zza(zzgjl zzgjlVar, boolean z) throws GeneralSecurityException {
        zzgjs zzf;
        zzf = zzf(zzgjlVar);
        this.zza.zza(zzf);
        this.zza.zzb(zzf.zza());
        return zzf.zza();
    }

    public final synchronized zzgam zzb() throws GeneralSecurityException {
        return zzgam.zza((zzgjt) this.zza.zzal());
    }

    @Deprecated
    public final synchronized zzgan zzc(zzgjl zzgjlVar) throws GeneralSecurityException {
        zza(zzgjlVar, true);
        return this;
    }
}
