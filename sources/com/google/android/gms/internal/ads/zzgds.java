package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgds implements zzglh {
    private final String zza;
    private final int zzb;
    private zzggn zzc;
    private zzgfp zzd;
    private int zze;
    private zzggz zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgds(zzgjl zzgjlVar) throws GeneralSecurityException {
        String zzf = zzgjlVar.zzf();
        this.zza = zzf;
        if (zzf.equals(zzgbg.zzb)) {
            try {
                zzggq zze = zzggq.zze(zzgjlVar.zze(), zzgnz.zza());
                this.zzc = (zzggn) zzgbe.zzd(zzgjlVar);
                this.zzb = zze.zza();
            } catch (zzgoz e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        } else if (zzf.equals(zzgbg.zza)) {
            try {
                zzgfs zzd = zzgfs.zzd(zzgjlVar.zze(), zzgnz.zza());
                this.zzd = (zzgfp) zzgbe.zzd(zzgjlVar);
                this.zze = zzd.zze().zza();
                this.zzb = this.zze + zzd.zzf().zza();
            } catch (zzgoz e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        } else if (zzf.equals(zzgdd.zza)) {
            try {
                zzghc zze2 = zzghc.zze(zzgjlVar.zze(), zzgnz.zza());
                this.zzf = (zzggz) zzgbe.zzd(zzgjlVar);
                this.zzb = zze2.zza();
            } catch (zzgoz e3) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e3);
            }
        } else {
            throw new GeneralSecurityException("unsupported AEAD DEM key type: ".concat(String.valueOf(zzf)));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzglh
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzglh
    public final zzgej zzb(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzb) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if (this.zza.equals(zzgbg.zzb)) {
            zzggm zzc = zzggn.zzc();
            zzc.zzaj(this.zzc);
            zzc.zza(zzgnf.zzw(bArr, 0, this.zzb));
            return new zzgej((zzfzs) zzgbe.zzh(this.zza, (zzggn) zzc.zzal(), zzfzs.class));
        } else if (this.zza.equals(zzgbg.zza)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zze);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zze, this.zzb);
            zzgfu zzc2 = zzgfv.zzc();
            zzc2.zzaj(this.zzd.zzf());
            zzc2.zza(zzgnf.zzv(copyOfRange));
            zzgii zzc3 = zzgij.zzc();
            zzc3.zzaj(this.zzd.zzg());
            zzc3.zza(zzgnf.zzv(copyOfRange2));
            zzgfo zzc4 = zzgfp.zzc();
            zzc4.zzc(this.zzd.zza());
            zzc4.zza((zzgfv) zzc2.zzal());
            zzc4.zzb((zzgij) zzc3.zzal());
            return new zzgej((zzfzs) zzgbe.zzh(this.zza, (zzgfp) zzc4.zzal(), zzfzs.class));
        } else if (this.zza.equals(zzgdd.zza)) {
            zzggy zzc5 = zzggz.zzc();
            zzc5.zzaj(this.zzf);
            zzc5.zza(zzgnf.zzw(bArr, 0, this.zzb));
            return new zzgej((zzfzy) zzgbe.zzh(this.zza, (zzggz) zzc5.zzal(), zzfzy.class));
        } else {
            throw new GeneralSecurityException("unknown DEM key type");
        }
    }
}
