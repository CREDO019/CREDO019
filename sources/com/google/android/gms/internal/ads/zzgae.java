package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
class zzgae implements zzgac {
    private final zzgem zza;
    private final Class zzb;

    public zzgae(zzgem zzgemVar, Class cls) {
        if (!zzgemVar.zzl().contains(cls) && !Void.class.equals(cls)) {
            throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", zzgemVar.toString(), cls.getName()));
        }
        this.zza = zzgemVar;
        this.zzb = cls;
    }

    private final zzgad zzg() {
        return new zzgad(this.zza.zza());
    }

    private final Object zzh(zzgpx zzgpxVar) throws GeneralSecurityException {
        if (Void.class.equals(this.zzb)) {
            throw new GeneralSecurityException("Cannot create a primitive for Void");
        }
        this.zza.zzd(zzgpxVar);
        return this.zza.zzk(zzgpxVar, this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzgac
    public final zzgjg zza(zzgnf zzgnfVar) throws GeneralSecurityException {
        try {
            zzgpx zza = zzg().zza(zzgnfVar);
            zzgjf zza2 = zzgjg.zza();
            zza2.zza(this.zza.zzc());
            zza2.zzb(zza.zzas());
            zza2.zzc(this.zza.zzf());
            return (zzgjg) zza2.zzal();
        } catch (zzgoz e) {
            throw new GeneralSecurityException("Unexpected proto", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgac
    public final zzgpx zzb(zzgnf zzgnfVar) throws GeneralSecurityException {
        try {
            return zzg().zza(zzgnfVar);
        } catch (zzgoz e) {
            throw new GeneralSecurityException("Failures parsing proto of type ".concat(String.valueOf(this.zza.zza().zzg().getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgac
    public final Class zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzgac
    public final Object zzd(zzgnf zzgnfVar) throws GeneralSecurityException {
        try {
            return zzh(this.zza.zzb(zzgnfVar));
        } catch (zzgoz e) {
            throw new GeneralSecurityException("Failures parsing proto of type ".concat(String.valueOf(this.zza.zzj().getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgac
    public final Object zze(zzgpx zzgpxVar) throws GeneralSecurityException {
        String concat = "Expected proto of type ".concat(String.valueOf(this.zza.zzj().getName()));
        if (this.zza.zzj().isInstance(zzgpxVar)) {
            return zzh(zzgpxVar);
        }
        throw new GeneralSecurityException(concat);
    }

    @Override // com.google.android.gms.internal.ads.zzgac
    public final String zzf() {
        return this.zza.zzc();
    }
}
