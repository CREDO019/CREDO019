package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgnu extends zzgmu {
    private static final Logger zza = Logger.getLogger(zzgnu.class.getName());
    private static final boolean zzb = zzgrr.zzA();
    zzgnv zze;

    private zzgnu() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgnu(zzgnt zzgntVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzA(zzgpx zzgpxVar, zzgqq zzgqqVar) {
        zzgmo zzgmoVar = (zzgmo) zzgpxVar;
        int zzar = zzgmoVar.zzar();
        if (zzar == -1) {
            zzar = zzgqqVar.zza(zzgmoVar);
            zzgmoVar.zzau(zzar);
        }
        return zzE(zzar) + zzar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzB(int r1) {
        if (r1 > 4096) {
            return 4096;
        }
        return r1;
    }

    public static int zzC(String str) {
        int length;
        try {
            length = zzgrw.zze(str);
        } catch (zzgrv unused) {
            length = str.getBytes(zzgox.zzb).length;
        }
        return zzE(length) + length;
    }

    public static int zzD(int r0) {
        return zzE(r0 << 3);
    }

    public static int zzE(int r1) {
        if ((r1 & (-128)) == 0) {
            return 1;
        }
        if ((r1 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & r1) == 0) {
            return 3;
        }
        return (r1 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzF(long j) {
        int r0;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            r0 = 6;
        } else {
            r0 = 2;
        }
        if (((-2097152) & j) != 0) {
            r0 += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? r0 + 1 : r0;
    }

    public static zzgnu zzG(byte[] bArr) {
        return new zzgnq(bArr, 0, bArr.length);
    }

    public static zzgnu zzH(OutputStream outputStream, int r2) {
        return new zzgns(outputStream, r2);
    }

    public static int zzw(zzgnf zzgnfVar) {
        int zzd = zzgnfVar.zzd();
        return zzE(zzd) + zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzx(int r2, zzgpx zzgpxVar, zzgqq zzgqqVar) {
        int zzE = zzE(r2 << 3);
        int r22 = zzE + zzE;
        zzgmo zzgmoVar = (zzgmo) zzgpxVar;
        int zzar = zzgmoVar.zzar();
        if (zzar == -1) {
            zzar = zzgqqVar.zza(zzgmoVar);
            zzgmoVar.zzau(zzar);
        }
        return r22 + zzar;
    }

    public static int zzy(int r0) {
        if (r0 >= 0) {
            return zzE(r0);
        }
        return 10;
    }

    public static int zzz(zzgpd zzgpdVar) {
        int zza2 = zzgpdVar.zza();
        return zzE(zza2) + zza2;
    }

    public final void zzI() {
        if (zzb() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzJ(String str, zzgrv zzgrvVar) throws IOException {
        zza.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzgrvVar);
        byte[] bytes = str.getBytes(zzgox.zzb);
        try {
            int length = bytes.length;
            zzs(length);
            zza(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzgnr(e);
        }
    }

    public abstract void zzN() throws IOException;

    public abstract void zzO(byte b) throws IOException;

    public abstract void zzP(int r1, boolean z) throws IOException;

    public abstract void zzQ(int r1, zzgnf zzgnfVar) throws IOException;

    @Override // com.google.android.gms.internal.ads.zzgmu
    public abstract void zza(byte[] bArr, int r2, int r3) throws IOException;

    public abstract int zzb();

    public abstract void zzh(int r1, int r2) throws IOException;

    public abstract void zzi(int r1) throws IOException;

    public abstract void zzj(int r1, long j) throws IOException;

    public abstract void zzk(long j) throws IOException;

    public abstract void zzl(int r1, int r2) throws IOException;

    public abstract void zzm(int r1) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzn(int r1, zzgpx zzgpxVar, zzgqq zzgqqVar) throws IOException;

    public abstract void zzo(int r1, String str) throws IOException;

    public abstract void zzq(int r1, int r2) throws IOException;

    public abstract void zzr(int r1, int r2) throws IOException;

    public abstract void zzs(int r1) throws IOException;

    public abstract void zzt(int r1, long j) throws IOException;

    public abstract void zzu(long j) throws IOException;
}
