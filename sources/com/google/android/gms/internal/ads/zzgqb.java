package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgqb implements zzgqq {
    private final zzgpx zza;
    private final zzgrh zzb;
    private final boolean zzc;
    private final zzgoa zzd;

    private zzgqb(zzgrh zzgrhVar, zzgoa zzgoaVar, zzgpx zzgpxVar) {
        this.zzb = zzgrhVar;
        this.zzc = zzgoaVar.zzh(zzgpxVar);
        this.zzd = zzgoaVar;
        this.zza = zzgpxVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgqb zzc(zzgrh zzgrhVar, zzgoa zzgoaVar, zzgpx zzgpxVar) {
        return new zzgqb(zzgrhVar, zzgoaVar, zzgpxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final int zza(Object obj) {
        zzgrh zzgrhVar = this.zzb;
        int zzb = zzgrhVar.zzb(zzgrhVar.zzd(obj));
        if (this.zzc) {
            this.zzd.zza(obj);
            throw null;
        }
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzd(obj).hashCode();
        if (this.zzc) {
            this.zzd.zza(obj);
            throw null;
        }
        return hashCode;
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final Object zze() {
        return this.zza.zzaL().zzan();
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzf(Object obj) {
        this.zzb.zzm(obj);
        this.zzd.zze(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzg(Object obj, Object obj2) {
        zzgqs.zzF(this.zzb, obj, obj2);
        if (this.zzc) {
            zzgqs.zzE(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzh(Object obj, zzgqi zzgqiVar, zzgnz zzgnzVar) throws IOException {
        boolean zzO;
        zzgrh zzgrhVar = this.zzb;
        zzgoa zzgoaVar = this.zzd;
        Object zzc = zzgrhVar.zzc(obj);
        zzgoe zzb = zzgoaVar.zzb(obj);
        while (zzgqiVar.zzc() != Integer.MAX_VALUE) {
            try {
                int zzd = zzgqiVar.zzd();
                if (zzd != 11) {
                    if ((zzd & 7) == 2) {
                        Object zzc2 = zzgoaVar.zzc(zzgnzVar, this.zza, zzd >>> 3);
                        if (zzc2 != null) {
                            zzgoaVar.zzf(zzgqiVar, zzc2, zzgnzVar, zzb);
                        } else {
                            zzO = zzgrhVar.zzp(zzc, zzgqiVar);
                        }
                    } else {
                        zzO = zzgqiVar.zzO();
                    }
                    if (!zzO) {
                        return;
                    }
                } else {
                    int r4 = 0;
                    Object obj2 = null;
                    zzgnf zzgnfVar = null;
                    while (zzgqiVar.zzc() != Integer.MAX_VALUE) {
                        int zzd2 = zzgqiVar.zzd();
                        if (zzd2 == 16) {
                            r4 = zzgqiVar.zzj();
                            obj2 = zzgoaVar.zzc(zzgnzVar, this.zza, r4);
                        } else if (zzd2 == 26) {
                            if (obj2 != null) {
                                zzgoaVar.zzf(zzgqiVar, obj2, zzgnzVar, zzb);
                            } else {
                                zzgnfVar = zzgqiVar.zzp();
                            }
                        } else if (!zzgqiVar.zzO()) {
                            break;
                        }
                    }
                    if (zzgqiVar.zzd() != 12) {
                        throw zzgoz.zzb();
                    } else if (zzgnfVar != null) {
                        if (obj2 != null) {
                            zzgoaVar.zzg(zzgnfVar, obj2, zzgnzVar, zzb);
                        } else {
                            zzgrhVar.zzk(zzc, r4, zzgnfVar);
                        }
                    }
                }
            } finally {
                zzgrhVar.zzn(obj, zzc);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzi(Object obj, byte[] bArr, int r3, int r4, zzgmr zzgmrVar) throws IOException {
        zzgon zzgonVar = (zzgon) obj;
        if (zzgonVar.zzc == zzgri.zzc()) {
            zzgonVar.zzc = zzgri.zze();
        }
        zzgok zzgokVar = (zzgok) obj;
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final boolean zzj(Object obj, Object obj2) {
        if (this.zzb.zzd(obj).equals(this.zzb.zzd(obj2))) {
            if (this.zzc) {
                this.zzd.zza(obj);
                this.zzd.zza(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final boolean zzk(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzn(Object obj, zzgnv zzgnvVar) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }
}
