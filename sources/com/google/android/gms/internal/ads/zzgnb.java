package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzgnb extends zzgna {
    protected final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgnb(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzgnf) && zzd() == ((zzgnf) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zzgnb) {
                zzgnb zzgnbVar = (zzgnb) obj;
                int zzr = zzr();
                int zzr2 = zzgnbVar.zzr();
                if (zzr == 0 || zzr2 == 0 || zzr == zzr2) {
                    return zzg(zzgnbVar, 0, zzd());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public byte zza(int r2) {
        return this.zza[r2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public byte zzb(int r2) {
        return this.zza[r2];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public int zzd() {
        return this.zza.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public void zze(byte[] bArr, int r3, int r4, int r5) {
        System.arraycopy(this.zza, r3, bArr, r4, r5);
    }

    @Override // com.google.android.gms.internal.ads.zzgna
    final boolean zzg(zzgnf zzgnfVar, int r7, int r8) {
        if (r8 > zzgnfVar.zzd()) {
            int zzd = zzd();
            throw new IllegalArgumentException("Length too large: " + r8 + zzd);
        }
        int r0 = r7 + r8;
        if (r0 > zzgnfVar.zzd()) {
            int zzd2 = zzgnfVar.zzd();
            throw new IllegalArgumentException("Ran off end of other: " + r7 + ", " + r8 + ", " + zzd2);
        } else if (zzgnfVar instanceof zzgnb) {
            zzgnb zzgnbVar = (zzgnb) zzgnfVar;
            byte[] bArr = this.zza;
            byte[] bArr2 = zzgnbVar.zza;
            int zzc = zzc() + r8;
            int zzc2 = zzc();
            int zzc3 = zzgnbVar.zzc() + r7;
            while (zzc2 < zzc) {
                if (bArr[zzc2] != bArr2[zzc3]) {
                    return false;
                }
                zzc2++;
                zzc3++;
            }
            return true;
        } else {
            return zzgnfVar.zzk(r7, r0).equals(zzk(0, r8));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final int zzi(int r3, int r4, int r5) {
        return zzgox.zzd(r3, this.zza, zzc() + r4, r5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final int zzj(int r2, int r3, int r4) {
        int zzc = zzc() + r3;
        return zzgrw.zzf(r2, this.zza, zzc, r4 + zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final zzgnf zzk(int r4, int r5) {
        int zzq = zzq(r4, r5, zzd());
        return zzq == 0 ? zzgnf.zzb : new zzgmy(this.zza, zzc() + r4, zzq);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final zzgnn zzl() {
        return zzgnn.zzI(this.zza, zzc(), zzd(), true);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    protected final String zzm(Charset charset) {
        return new String(this.zza, zzc(), zzd(), charset);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final ByteBuffer zzn() {
        return ByteBuffer.wrap(this.zza, zzc(), zzd()).asReadOnlyBuffer();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final void zzo(zzgmu zzgmuVar) throws IOException {
        zzgmuVar.zza(this.zza, zzc(), zzd());
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final boolean zzp() {
        int zzc = zzc();
        return zzgrw.zzj(this.zza, zzc, zzd() + zzc);
    }
}
