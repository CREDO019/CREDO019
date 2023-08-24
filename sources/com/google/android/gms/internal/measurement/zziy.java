package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public class zziy extends zzix {
    protected final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziy(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzjb) && zzd() == ((zzjb) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zziy) {
                zziy zziyVar = (zziy) obj;
                int zzk = zzk();
                int zzk2 = zziyVar.zzk();
                if (zzk == 0 || zzk2 == 0 || zzk == zzk2) {
                    int zzd = zzd();
                    if (zzd > zziyVar.zzd()) {
                        int zzd2 = zzd();
                        throw new IllegalArgumentException("Length too large: " + zzd + zzd2);
                    } else if (zzd > zziyVar.zzd()) {
                        int zzd3 = zziyVar.zzd();
                        throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzd3);
                    } else if (zziyVar instanceof zziy) {
                        byte[] bArr = this.zza;
                        byte[] bArr2 = zziyVar.zza;
                        zziyVar.zzc();
                        int r9 = 0;
                        int r5 = 0;
                        while (r9 < zzd) {
                            if (bArr[r9] != bArr2[r5]) {
                                return false;
                            }
                            r9++;
                            r5++;
                        }
                        return true;
                    } else {
                        return zziyVar.zzf(0, zzd).equals(zzf(0, zzd));
                    }
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public byte zza(int r2) {
        return this.zza[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzjb
    public byte zzb(int r2) {
        return this.zza[r2];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    protected final int zze(int r2, int r3, int r4) {
        return zzkk.zzd(r2, this.zza, 0, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final zzjb zzf(int r3, int r4) {
        int zzj = zzj(0, r4, zzd());
        return zzj == 0 ? zzjb.zzb : new zziv(this.zza, 0, zzj);
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    protected final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzjb
    public final void zzh(zzir zzirVar) throws IOException {
        ((zzjg) zzirVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.measurement.zzjb
    public final boolean zzi() {
        return zzna.zzf(this.zza, 0, zzd());
    }
}
